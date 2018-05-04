package namnh.com.mvpkotlin.features.tasklist

import namnh.com.mvpkotlin.data.model.Task
import namnh.com.mvpkotlin.features.BasePresenter
import namnh.com.mvpkotlin.features.BaseView

/**
 * Created by namnh on 03/05/2018.
 */
interface TaskListContract {
  interface View : BaseView<Presenter> {
    fun showTasks(tasks: List<Task>)
    fun showError(throwable: Throwable)
  }

  interface Presenter : BasePresenter<View> {
    fun getTasks(uid: String)
  }
}