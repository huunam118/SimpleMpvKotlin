package namnh.com.mvpkotlin.features.tasklist

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import namnh.com.mvpkotlin.R
import javax.inject.Inject

/**
 * Created by namnh on 03/05/2018.
 */
class TaskListActivity : DaggerAppCompatActivity() {

  @Inject
  lateinit var taskListFragment: TaskListFragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_list)
    var taskListFragment = supportFragmentManager.findFragmentById(R.id.container)
    if (taskListFragment == null) {
      taskListFragment = this.taskListFragment
      supportFragmentManager.beginTransaction().add(R.id.container, taskListFragment).commit()
    }
  }

}