package namnh.com.mvpkotlin.features.tasklist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import namnh.com.mvpkotlin.R

/**
 * Created by namnh on 03/05/2018.
 */
class TaskListActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_task_list)
    val taskListFragment = supportFragmentManager.findFragmentById(R.id.container)
    if (taskListFragment == null) {
      supportFragmentManager.beginTransaction().add(R.id.container, TaskListFragment()).commit()
    }
  }
}
