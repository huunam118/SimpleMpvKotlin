package namnh.com.mvpkotlin.features.tasklist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import namnh.com.mvpkotlin.MvpApplication
import namnh.com.mvpkotlin.R
import namnh.com.mvpkotlin.data.model.Task
import javax.inject.Inject

/**
 * Created by namnh on 04/05/2018.
 */
class TaskListFragment : Fragment(), TaskListContract.View {

  @Inject
  lateinit var presenter: TaskListContract.Presenter

  @BindView(R.id.text_info)
  private lateinit var textInfo: TextView

  @BindView(R.id.recycler_task)
  private lateinit var recyclerTask: RecyclerView

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_task_list, container, false)
    ButterKnife.bind(this, view)
    return view
  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    DaggerTaskListComponent.builder()
      .taskListModule(TaskListModule(this))
      .appComponent((activity?.application as MvpApplication).getAppComponent()).build()
      .inject(this)
  }

  override fun onResume() {
    super.onResume()
    presenter.takeView(this)
  }

  override fun onDestroy() {
    presenter.dropView()
    super.onDestroy()
  }

  override fun showTasks(tasks: List<Task>) {
    TODO("Show all the tasks")
  }

  override fun showError(throwable: Throwable) {
    TODO("Show the error dialog")
  }
}