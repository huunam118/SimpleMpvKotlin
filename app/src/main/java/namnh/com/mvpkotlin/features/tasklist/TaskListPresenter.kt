package namnh.com.mvpkotlin.features.tasklist

import io.reactivex.android.schedulers.AndroidSchedulers
import namnh.com.mvpkotlin.data.source.TaskRepository

class TaskListPresenter(private val view: TaskListContract.View,
    private val taskRepo: TaskRepository) : TaskListContract.Presenter {

  override fun start() {
    // Todo
  }

  override fun stop() {
    // Todo
  }

  override fun getTasks(uid: String) {
    taskRepo.getTasksByOwner(uid).observeOn(AndroidSchedulers.mainThread()).subscribe(
        { tasks -> view.showTasks(tasks) },
        { throwable -> view.showError(throwable) })
  }
}
