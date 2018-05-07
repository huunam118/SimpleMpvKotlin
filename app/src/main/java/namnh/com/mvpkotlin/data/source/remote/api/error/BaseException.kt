package namnh.com.mvpkotlin.data.source.remote.api.error

import android.support.annotation.StringDef
import namnh.com.mvpkotlin.data.source.remote.api.responses.BaseErrorResponse
import retrofit2.Response

/**
 * Created by namnh on 03/05/2018.
 */
class BaseException : RuntimeException {

  @Kind
  private var kind: String
  private lateinit var errorResponse: BaseErrorResponse
  private lateinit var response: Response<*>

  constructor(@Kind kind: String, cause: Throwable) : super(cause.message, cause) {
    this.kind = kind
  }

  constructor(@Kind kind: String, errorResponse: BaseErrorResponse) {
    this.kind = kind
    this.errorResponse = errorResponse
  }

  constructor(@Kind kind: String, response: Response<*>) {
    this.kind = kind
    this.response = response
  }

  companion object {
    const val NETWORK = "NETWORK"
    const val HTTP = "HTTP"
    const val SERVER = "SERVER"
    const val UNEXPECTED = "UNEXPECTED"

    fun toNetworkError(cause: Throwable): BaseException {
      return BaseException(NETWORK, cause)
    }

    fun toHttpError(response: Response<*>): BaseException {
      return BaseException(HTTP, response)
    }

    fun toServerError(baseErrorResponse: BaseErrorResponse): BaseException {
      return BaseException(SERVER, baseErrorResponse)
    }

    fun toUnexpectedError(cause: Throwable): BaseException {
      return BaseException(UNEXPECTED, cause)
    }
  }

  @StringDef(value = [NETWORK, HTTP, SERVER, UNEXPECTED])
  annotation class Kind
}
