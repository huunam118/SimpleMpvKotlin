package namnh.com.mvpkotlin.data.source.remote

import namnh.com.mvpkotlin.data.source.remote.api.services.AuthApi
import namnh.com.mvpkotlin.data.source.remote.api.services.NonAuthApi
import javax.inject.Inject

/**
 * Created by namnh on 02/05/2018.
 */
class UserRemoteDataSource @Inject
constructor(authApi: AuthApi, nonAuthApi: NonAuthApi) : RemoteDataSource(authApi,
    nonAuthApi)
