package namnh.com.mvpkotlin.features.tasklist

import dagger.Module
import dagger.Provides
import namnh.com.mvpkotlin.data.source.TaskRepository
import namnh.com.mvpkotlin.di.scopes.ActivityScope

@Module
class TaskListModule(private val view: TaskListContract.View) {

  @Provides
  @ActivityScope
  fun providePresenter(taskRepo: TaskRepository): TaskListContract.Presenter = TaskListPresenter(
      view, taskRepo)
}
