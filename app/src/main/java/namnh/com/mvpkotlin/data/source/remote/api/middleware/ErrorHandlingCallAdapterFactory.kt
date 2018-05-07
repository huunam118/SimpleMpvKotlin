package namnh.com.mvpkotlin.data.source.remote.api.middleware

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import namnh.com.mvpkotlin.data.source.remote.api.error.BaseException
import namnh.com.mvpkotlin.data.source.remote.api.responses.BaseErrorResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type


/**
 * Created by namnh on 02/05/2018.
 */

class ErrorHandlingCallAdapterFactory : CallAdapter.Factory() {

  private val original by lazy {
    RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
  }

  companion object {
    fun create(): CallAdapter.Factory = ErrorHandlingCallAdapterFactory()
  }

  override fun get(
    returnType: Type, annotations: Array<out Annotation>,
    retrofit: Retrofit
  ): CallAdapter<*, *>? {
    val wrapped = original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
    return RxCallAdapterWrapper(wrapped)
  }

  private class RxCallAdapterWrapper<R> constructor(
    private val wrapped: CallAdapter<R, *>
  ) : CallAdapter<R, Observable<R>> {

    @Suppress("UNCHECKED_CAST")
    override fun adapt(call: Call<R>): Observable<R> {
      return (wrapped.adapt(call) as Observable<R>).onErrorResumeNext { throwable: Throwable ->
        Observable.error(asRetrofitException(throwable))
      }
    }

    override fun responseType(): Type {
      return wrapped.responseType()
    }

    private fun asRetrofitException(throwable: Throwable): BaseException {
      when (throwable) {
        is BaseException -> return throwable
        is IOException -> return BaseException.toNetworkError(throwable)
        is HttpException -> {
          val response = throwable.response()
          return if (response.errorBody() != null) {
            try {
              val errorResponse = response.errorBody()?.string()
              val baseErrorException = errorResponse?.let { deserializeErrorBody(it) }
              if (baseErrorException != null && baseErrorException.hasError()) {
                BaseException.toServerError(baseErrorException)
              } else {
                BaseException.toHttpError(response)
              }
            } catch (e: IOException) {
              BaseException.toUnexpectedError(throwable)
            }
          } else {
            BaseException.toHttpError(response)
          }
        }
        else -> return BaseException.toUnexpectedError(throwable)
      }
    }

    private fun deserializeErrorBody(errorString: String): BaseErrorResponse? {
      val gson = GsonBuilder().create()
      return try {
        gson.fromJson<BaseErrorResponse>(errorString, BaseErrorResponse::class.java)
      } catch (e: JsonSyntaxException) {
        null
      }
    }
  }
}