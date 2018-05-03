package namnh.com.mvpkotlin.di.components

import android.content.Context
import com.google.gson.Gson
import dagger.Component
import namnh.com.mvpkotlin.data.source.TaskRepository
import namnh.com.mvpkotlin.data.source.UserRepository
import namnh.com.mvpkotlin.data.source.local.api.AppDatabase
import namnh.com.mvpkotlin.di.modules.AppModule
import namnh.com.mvpkotlin.di.modules.NetworkModule
import namnh.com.mvpkotlin.di.modules.RepositoryModule
import namnh.com.mvpkotlin.di.scopes.AppScope

/**
 * Created by namnh on 02/05/2018.
 */
@AppScope
@Component(modules = [AppModule::class, RepositoryModule::class, NetworkModule::class])
interface AppComponent {
  fun context(): Context
  fun taskRepo(): TaskRepository
  fun userRepo(): UserRepository
  fun appDatabase(): AppDatabase
  fun gson(): Gson
}
