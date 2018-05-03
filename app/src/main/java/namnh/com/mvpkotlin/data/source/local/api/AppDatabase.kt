package namnh.com.mvpkotlin.data.source.local.api

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import namnh.com.mvpkotlin.data.model.Task
import namnh.com.mvpkotlin.data.model.User
import namnh.com.mvpkotlin.data.source.local.api.AppDatabase.Companion.DB_VERSION
import namnh.com.mvpkotlin.data.source.local.api.dao.TaskDao
import namnh.com.mvpkotlin.data.source.local.api.dao.UserDao

/**
 * Created by namnh on 02/05/2018.
 */
@Database(entities = [User::class, Task::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  companion object {
    private const val DB_NAME = "app_db"
    const val DB_VERSION = 1

    private var instance: AppDatabase? = null
    private val lock = Any()
    fun getInstance(context: Context): AppDatabase {
      synchronized(lock) {
        if (instance == null) {
          instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,
              DB_NAME).build()
        }
        return instance!!
      }
    }
  }

  abstract fun userDao(): UserDao
  abstract fun taskDao(): TaskDao
}