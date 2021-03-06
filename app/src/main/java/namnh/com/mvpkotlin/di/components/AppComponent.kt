package namnh.com.mvpkotlin.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import namnh.com.mvpkotlin.MvpApplication
import namnh.com.mvpkotlin.di.modules.ActivityBindingModule
import namnh.com.mvpkotlin.di.modules.AppModule
import namnh.com.mvpkotlin.di.modules.NetworkModule
import namnh.com.mvpkotlin.di.modules.RepositoryModule
import namnh.com.mvpkotlin.di.scopes.AppScope

/**
 * Created by namnh on 02/05/2018.
 */
@AppScope
@Component(
  modules = [AppModule::class, AndroidSupportInjectionModule::class,
    ActivityBindingModule::class, RepositoryModule::class, NetworkModule::class]
)
interface AppComponent : AndroidInjector<MvpApplication> {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(app: Application): Builder

    fun build(): AppComponent
  }
}
