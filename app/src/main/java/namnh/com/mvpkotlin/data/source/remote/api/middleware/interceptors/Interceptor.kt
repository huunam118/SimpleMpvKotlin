package namnh.com.mvpkotlin.data.source.remote.api.middleware.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by namnh on 02/05/2018.
 */
abstract class Interceptor(protected val context: Context) : Interceptor {
  override fun intercept(chain: Interceptor.Chain?): Response {
    return chain!!.proceed(chain.request())
  }
}