package namnh.com.mvpkotlin.data.source.local

import io.reactivex.Observable
import io.reactivex.functions.BiConsumer
import namnh.com.mvpkotlin.data.model.Task
import namnh.com.mvpkotlin.data.source.local.api.RoomApi
import javax.inject.Inject

/**
 * Created by namnh on 02/05/2018.
 */
class TaskLocalDataSource @Inject
constructor(roomApi: RoomApi) : LocalDataSource(roomApi) {
  fun getById(uid: String): Observable<Task> {
    return query(
        BiConsumer { subscriber, roomApi ->
          subscriber.onNext(roomApi.taskDao().getById(uid))
        })
  }

  fun saveTask(task: Task): Observable<Task> {
    return execute(BiConsumer { subscriber, roomApi ->
      roomApi.taskDao().save(task)
      subscriber.onNext(task)
    })
  }

  fun saveTasks(tasks: List<Task>): Observable<List<Task>> {
    return execute(BiConsumer { subscriber, roomApi ->
      roomApi.taskDao().save(tasks)
      subscriber.onNext(tasks)
    })
  }
}