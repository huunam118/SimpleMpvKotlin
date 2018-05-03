package namnh.com.mvpkotlin.data.source.remote

import namnh.com.mvpkotlin.data.source.remote.api.services.AuthApi
import namnh.com.mvpkotlin.data.source.remote.api.services.NonAuthApi

/**
 * Created by namnh on 02/05/2018.
 */
abstract class RemoteDataSource(protected val authApi: AuthApi,
    protected val nonAuthApi: NonAuthApi)