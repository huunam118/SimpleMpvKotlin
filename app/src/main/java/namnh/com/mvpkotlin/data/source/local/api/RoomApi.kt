package namnh.com.mvpkotlin.data.source.local.api

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.BiConsumer
import namnh.com.mvpkotlin.data.source.local.api.dao.TaskDao
import namnh.com.mvpkotlin.data.source.local.api.dao.UserDao

/**
 * Created by namnh on 02/05/2018.
 */
interface RoomApi {
  fun userDao(): UserDao
  fun taskDao(): TaskDao

  fun <T> execute(action: BiConsumer<Observer<in T>, RoomApi>): Observable<T>
  fun <T> query(action: BiConsumer<Observer<in T>, RoomApi>): Observable<T>
}