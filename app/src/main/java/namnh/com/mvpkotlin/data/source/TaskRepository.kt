package namnh.com.mvpkotlin.data.source

import io.reactivex.Observable
import namnh.com.mvpkotlin.data.model.Task
import namnh.com.mvpkotlin.data.source.local.TaskLocalDataSource
import namnh.com.mvpkotlin.data.source.remote.TaskRemoteDataSource

/**
 * Created by namnh on 02/05/2018.
 */
class TaskRepository(private val localDataSource: TaskLocalDataSource,
    private val remoteDataSource: TaskRemoteDataSource) : Repository {
  fun getTasksByOwner(uid: String): Observable<List<Task>> {
    return remoteDataSource.getTasksByOwner(
        uid).flatMap { tasks -> localDataSource.saveTasks(tasks) }
  }

  fun getById(uid: String): Observable<Task> {
    return localDataSource.getById(uid)
  }

  fun createTask(task: Task): Observable<Task> {
    return remoteDataSource.createTask(task).flatMap { newTask ->
      localDataSource.saveTask(newTask)
    }
  }

  fun updateTaskStatus(uid: String, status: String): Observable<Task> {
    return remoteDataSource.updateTaskStatus(uid, status)
        .flatMap { updatedTask -> localDataSource.saveTask(updatedTask) }
  }
}