package namnh.com.mvpkotlin.features.tasklist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import namnh.com.mvpkotlin.MvpApplication
import namnh.com.mvpkotlin.R
import namnh.com.mvpkotlin.data.model.Task
import javax.inject.Inject

/**
 * Created by namnh on 03/05/2018.
 */
class TaskListActivity : AppCompatActivity(), TaskListContract.View {

  @Inject
  lateinit var presenter: TaskListContract.Presenter

  @BindView(R.id.recycler_task)
  lateinit var recyclerTask: RecyclerView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    DaggerTaskListComponent.builder()
        .appComponent((application as MvpApplication).getAppComponent())
        .taskListModule(TaskListModule(this))
        .build()
        .inject(this)
    ButterKnife.bind(this)
  }

  override fun onStart() {
    super.onStart()
    presenter.getTasks("my-uid")
  }

  override fun showTasks(tasks: List<Task>) {
    // show list tasks
  }

  override fun showError(throwable: Throwable) {
    // show error dialog
  }
}