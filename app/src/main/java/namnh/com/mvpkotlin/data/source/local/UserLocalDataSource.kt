package namnh.com.mvpkotlin.data.source.local

import namnh.com.mvpkotlin.data.source.local.api.RoomApi
import javax.inject.Inject

/**
 * Created by namnh on 02/05/2018.
 */
class UserLocalDataSource @Inject
constructor(roomApi: RoomApi) : LocalDataSource(roomApi)