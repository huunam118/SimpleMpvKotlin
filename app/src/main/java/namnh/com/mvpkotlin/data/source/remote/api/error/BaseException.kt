package namnh.com.mvpkotlin.data.source.remote.api.error

import android.support.annotation.StringDef
import namnh.com.mvpkotlin.data.source.remote.api.responses.Response

/**
 * Created by namnh on 03/05/2018.
 */
class BaseException(@Kind val kind: String, val throwable: Throwable) : RuntimeException() {

  constructor(@Kind kind: String, response: Response) : this(kind, Throwable())

  companion object {
    const val NETWORK = "NETWORK"
    const val HTTP = "HTTP"
    const val SERVER = "SERVER"
    const val UNEXPECTED = "UNEXPECTED"

    fun toNetworkError(throwable: Throwable): BaseException {
      return BaseException(NETWORK, throwable)
    }

    @StringDef(value = [NETWORK, HTTP, SERVER, UNEXPECTED])
    annotation class Kind
  }
}