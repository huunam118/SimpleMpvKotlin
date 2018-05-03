package namnh.com.mvpkotlin.data.source.remote.api.middleware.interceptors

import android.content.Context
import okhttp3.Response

/**
 * Created by namnh on 02/05/2018.
 */
class AuthInterceptor(context: Context) : Interceptor(context) {

  override fun intercept(chain: okhttp3.Interceptor.Chain?): Response {
    return super.intercept(chain)
  }
}