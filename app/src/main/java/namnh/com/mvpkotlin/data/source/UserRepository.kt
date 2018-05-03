package namnh.com.mvpkotlin.data.source

import namnh.com.mvpkotlin.data.source.local.UserLocalDataSource
import namnh.com.mvpkotlin.data.source.remote.UserRemoteDataSource

/**
 * Created by namnh on 02/05/2018.
 */
class UserRepository(private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource) : Repository