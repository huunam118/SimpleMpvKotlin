package namnh.com.mvpkotlin.data.source.local.api

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiConsumer
import namnh.com.mvpkotlin.data.source.local.api.dao.TaskDao
import namnh.com.mvpkotlin.data.source.local.api.dao.UserDao

/**
 * Created by namnh on 02/05/2018.
 */
class RoomApiImpl(private val database: AppDatabase) : RoomApi {
  override fun userDao(): UserDao = database.userDao()

  override fun taskDao(): TaskDao = database.taskDao()

  override fun <T> execute(action: BiConsumer<Observer<in T>, RoomApi>): Observable<T> {
    return Observable.unsafeCreate { observer ->
      try {
        action.accept(observer, this)
        observer.onComplete()
      } catch (e: Exception) {
        observer.onError(e)
      }
    }
  }

  override fun <T> query(action: BiConsumer<Observer<in T>, RoomApi>): Observable<T> {
    return execute(action).observeOn(AndroidSchedulers.mainThread())
  }
}