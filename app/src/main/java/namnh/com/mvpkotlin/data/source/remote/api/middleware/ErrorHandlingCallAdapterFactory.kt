package namnh.com.mvpkotlin.data.source.remote.api.middleware

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import namnh.com.mvpkotlin.data.source.remote.api.error.BaseException
import retrofit2.Call
import retrofit2.CallAdapter
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

  private class RxCallAdapterWrapper<R> constructor(private val retrofit: Retrofit,
      private val wrapped: CallAdapter<R, *>) : CallAdapter<R, Observable<R>> {

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
      }
      return BaseException(BaseException.UNEXPECTED, throwable)
    }
  }

  override fun get(returnType: Type, annotations: Array<out Annotation>,
      retrofit: Retrofit): CallAdapter<*, *>? {
    val wrapped = original.get(returnType, annotations, retrofit) as CallAdapter<out Any, *>
    return RxCallAdapterWrapper(retrofit, wrapped)
  }
}