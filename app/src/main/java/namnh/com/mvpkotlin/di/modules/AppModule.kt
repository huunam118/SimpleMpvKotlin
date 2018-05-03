package namnh.com.mvpkotlin.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import namnh.com.mvpkotlin.di.scopes.AppScope

/**
 * Created by namnh on 02/05/2018.
 */
@Module
class AppModule(private val appContext: Context) {

  @Provides
  @AppScope
  fun provideAppContext(): Context = appContext
}