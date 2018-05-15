package namnh.com.mvpkotlin.features.tasklist

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import dagger.android.support.DaggerFragment
import namnh.com.mvpkotlin.R
import namnh.com.mvpkotlin.data.model.Task
import namnh.com.mvpkotlin.di.scopes.ActivityScope
import javax.inject.Inject

/**
 * Created by namnh on 04/05/2018.
 */
@ActivityScope
class TaskListFragment @Inject constructor() : DaggerFragment(), TaskListContract.View {

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
