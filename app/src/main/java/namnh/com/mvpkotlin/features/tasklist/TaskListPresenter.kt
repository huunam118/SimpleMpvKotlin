package namnh.com.mvpkotlin.features.tasklist

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import namnh.com.mvpkotlin.data.source.TaskRepository

class TaskListPresenter(private val taskRepo: TaskRepository) : TaskListContract.Presenter {

  private val disposable = CompositeDisposable()
  private var tasksView: TaskListContract.View? = null

  override fun takeView(view: TaskListContract.View) {
    tasksView = view
  }

  override fun dropView() {
    tasksView = null
  }

  override fun getTasks(uid: String) {
    disposable.add(
      taskRepo.getTasksByOwner(uid).observeOn(AndroidSchedulers.mainThread()).subscribe(
        { tasks -> tasksView?.showTasks(tasks) },
        { throwable -> tasksView?.showError(throwable) })
    )
  }
}
