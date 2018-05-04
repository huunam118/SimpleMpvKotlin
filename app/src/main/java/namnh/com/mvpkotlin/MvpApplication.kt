package namnh.com.mvpkotlin

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import namnh.com.mvpkotlin.di.components.DaggerAppComponent

/**
 * Created by namnh on 02/05/2018.
 */
class MvpApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
    DaggerAppComponent.builder().application(this).build()
}