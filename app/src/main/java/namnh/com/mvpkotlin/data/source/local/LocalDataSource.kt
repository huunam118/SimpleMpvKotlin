package namnh.com.mvpkotlin.data.source.local

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.functions.BiConsumer
import namnh.com.mvpkotlin.data.source.local.api.RoomApi

/**
 * Created by namnh on 02/05/2018.
 */
abstract class LocalDataSource(private val roomApi: RoomApi) {
  fun <T> execute(action: BiConsumer<Observer<in T>, RoomApi>): Observable<T> = roomApi.execute(action)
  fun <T> query(action: BiConsumer<Observer<in T>, RoomApi>): Observable<T> = roomApi.query(action)
}