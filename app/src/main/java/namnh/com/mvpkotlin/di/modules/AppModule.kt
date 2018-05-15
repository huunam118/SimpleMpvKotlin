package namnh.com.mvpkotlin.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import namnh.com.mvpkotlin.di.scopes.AppScope

/**
 * Created by namnh on 02/05/2018.
 */
@Module
abstract class AppModule {

  // same as @Provides but this returns injected parameter
  @Binds
  @AppScope
  abstract fun provideAppContext(application: Application): Context
}
