package namnh.com.mvpkotlin.di.modules

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import namnh.com.mvpkotlin.data.source.remote.api.middleware.interceptors.AuthInterceptor
import namnh.com.mvpkotlin.data.source.remote.api.middleware.interceptors.NonAuthInterceptor
import namnh.com.mvpkotlin.data.source.remote.api.services.AuthApi
import namnh.com.mvpkotlin.data.source.remote.api.services.NonAuthApi
import namnh.com.mvpkotlin.data.source.remote.api.services.ServiceGenerator
import namnh.com.mvpkotlin.di.scopes.AppScope
import okhttp3.Cache

/**
 * Created by namnh on 02/05/2018.
 */
@Module
class NetworkModule {
  @Provides
  @AppScope
  fun provideOkHttpCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024 // 10 MiB
    return Cache(application.cacheDir, cacheSize.toLong())
  }

  @Provides
  @AppScope
  fun provideGson(): Gson {
    return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
  }

  @Provides
  @AppScope
  fun provideAuthInterceptor(context: Context): AuthInterceptor {
    return AuthInterceptor(context)
  }

  @Provides
  @AppScope
  fun provideNonAuthInterceptor(context: Context): NonAuthInterceptor {
    return NonAuthInterceptor(context)
  }

  @Provides
  @AppScope
  fun provideAuthApi(gson: Gson, interceptor: AuthInterceptor): AuthApi {
    return ServiceGenerator.createService("http://google.com", AuthApi::class.java, gson,
        interceptor)
  }

  @Provides
  @AppScope
  fun provideNonAuthApi(gson: Gson, interceptor: NonAuthInterceptor): NonAuthApi {
    return ServiceGenerator.createService("http://google.com", NonAuthApi::class.java, gson,
        interceptor)
  }
}
