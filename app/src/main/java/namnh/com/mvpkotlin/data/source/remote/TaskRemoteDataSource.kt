package namnh.com.mvpkotlin.data.source.remote

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import namnh.com.mvpkotlin.data.model.Task
import namnh.com.mvpkotlin.data.source.remote.api.requests.CreateTaskRequest
import namnh.com.mvpkotlin.data.source.remote.api.services.AuthApi
import namnh.com.mvpkotlin.data.source.remote.api.services.NonAuthApi
import javax.inject.Inject

/**
 * Created by namnh on 02/05/2018.
 */

class TaskRemoteDataSource @Inject
constructor(authApi: AuthApi, nonAuthApi: NonAuthApi) : RemoteDataSource(authApi, nonAuthApi) {

  fun getTasksByOwner(uid: String): Observable<List<Task>> {
    return authApi.getTasks(uid).subscribeOn(Schedulers.io())
  }

  fun createTask(task: Task): Observable<Task> {
    return authApi.createTask(CreateTaskRequest(task)).subscribeOn(Schedulers.io())
  }

  fun updateTaskStatus(uid: String, status: String): Observable<Task> {
    return authApi.updateTaskStatus(uid, status).subscribeOn(Schedulers.io())
  }
}