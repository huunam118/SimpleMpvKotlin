package namnh.com.mvpkotlin.di.modules

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import namnh.com.mvpkotlin.di.scopes.ActivityScope
import namnh.com.mvpkotlin.features.tasklist.TaskListActivity
import namnh.com.mvpkotlin.features.tasklist.TaskListModule

/**
 * Created by namnh on 04/05/2018.
 */
@Module(includes = [AndroidInjectionModule::class])
abstract class ActivityBindingModule {
  @ActivityScope
  @ContributesAndroidInjector(modules = [TaskListModule::class])
  abstract fun bindTaskListActivity(): TaskListActivity
}
