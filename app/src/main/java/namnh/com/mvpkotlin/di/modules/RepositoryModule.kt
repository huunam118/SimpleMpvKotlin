package namnh.com.mvpkotlin.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import namnh.com.mvpkotlin.data.source.TaskRepository
import namnh.com.mvpkotlin.data.source.UserRepository
import namnh.com.mvpkotlin.data.source.local.TaskLocalDataSource
import namnh.com.mvpkotlin.data.source.local.UserLocalDataSource
import namnh.com.mvpkotlin.data.source.local.api.AppDatabase
import namnh.com.mvpkotlin.data.source.local.api.RoomApi
import namnh.com.mvpkotlin.data.source.local.api.RoomApiImpl
import namnh.com.mvpkotlin.data.source.remote.TaskRemoteDataSource
import namnh.com.mvpkotlin.data.source.remote.UserRemoteDataSource
import namnh.com.mvpkotlin.di.scopes.AppScope

/**
 * Created by namnh on 02/05/2018.
 */
@Module
class RepositoryModule {

  @Provides
  @AppScope
  fun provideTaskRepo(localDataSource: TaskLocalDataSource,
      remoteDataSource: TaskRemoteDataSource): TaskRepository {
    return TaskRepository(localDataSource, remoteDataSource)
  }

  @Provides
  @AppScope
  fun provideUserRepo(localDataSource: UserLocalDataSource,
      remoteDataSource: UserRemoteDataSource): UserRepository {
    return UserRepository(localDataSource, remoteDataSource)
  }

  @Provides
  @AppScope
  fun provideRoomApi(appDatabase: AppDatabase): RoomApi {
    return RoomApiImpl(appDatabase)
  }

  @Provides
  @AppScope
  fun provideDatabaseManager(context: Context): AppDatabase {
    return AppDatabase.getInstance(context)
  }
}
