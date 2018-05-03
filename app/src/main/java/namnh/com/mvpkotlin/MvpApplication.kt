package namnh.com.mvpkotlin

import android.app.Application
import namnh.com.mvpkotlin.di.components.AppComponent
import namnh.com.mvpkotlin.di.components.DaggerAppComponent
import namnh.com.mvpkotlin.di.modules.AppModule
import namnh.com.mvpkotlin.di.modules.NetworkModule
import namnh.com.mvpkotlin.di.modules.RepositoryModule

/**
 * Created by namnh on 02/05/2018.
 */
class MvpApplication : Application() {

  private lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()

    appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).networkModule(
        NetworkModule()).repositoryModule(RepositoryModule()).build()
  }

  fun getAppComponent(): AppComponent {
    return appComponent
  }
}