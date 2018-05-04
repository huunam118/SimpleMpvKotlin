package namnh.com.mvpkotlin.features.tasklist

import dagger.Module
import dagger.Provides
import namnh.com.mvpkotlin.data.source.TaskRepository
import namnh.com.mvpkotlin.di.scopes.FragmentScope

@Module
@FragmentScope
class TaskListModule(private val taskListFragment: TaskListFragment) {
  @Provides
  @FragmentScope
  fun providePresenter(taskRepo: TaskRepository): TaskListContract.Presenter =
    TaskListPresenter(taskRepo)
}
