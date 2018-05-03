package namnh.com.mvpkotlin.data.source.remote.api.services

import android.support.annotation.NonNull
import com.google.gson.Gson
import namnh.com.mvpkotlin.BuildConfig
import namnh.com.mvpkotlin.data.source.remote.api.middleware.ErrorHandlingCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by namnh on 02/05/2018.
 */
class ServiceGenerator {
  companion object {
    private const val CONNECTION_TIMEOUT: Long = 30
    @JvmStatic
    fun <T> createService(@NonNull endpoint: String,
        serviceClass: Class<T>, @NonNull gson: Gson, @NonNull interceptor: Interceptor): T {
      val httpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(
          interceptor).readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS).connectTimeout(
          CONNECTION_TIMEOUT, TimeUnit.SECONDS)

      val retrofitBuilder: Retrofit.Builder = Retrofit.Builder().baseUrl(
          endpoint).addCallAdapterFactory(
          ErrorHandlingCallAdapterFactory.create()).addConverterFactory(
          GsonConverterFactory.create(gson))

      if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(logging)
      }

      val retrofit: Retrofit = retrofitBuilder.client(
          httpClientBuilder.build()).build()

      return retrofit.create(serviceClass)
    }
  }
}