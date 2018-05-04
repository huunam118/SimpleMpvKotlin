package namnh.com.mvpkotlin.features.tasklist

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import namnh.com.mvpkotlin.data.source.TaskRepository
import namnh.com.mvpkotlin.di.scopes.ActivityScope
import namnh.com.mvpkotlin.di.scopes.FragmentScope

@Module
abstract class TaskListModule {

  @Module
  companion object {
    @JvmStatic
    @Provides
    @ActivityScope
    fun providePresenter(taskRepo: TaskRepository): TaskListContract.Presenter =
      TaskListPresenter(taskRepo)
  }

  @FragmentScope
  @ContributesAndroidInjector
  abstract fun taskListFragment(): TaskListFragment
}
