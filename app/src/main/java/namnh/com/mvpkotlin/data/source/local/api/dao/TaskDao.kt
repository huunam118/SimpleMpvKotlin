package namnh.com.mvpkotlin.data.source.local.api.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import namnh.com.mvpkotlin.data.model.Task

/**
 * Created by namnh on 02/05/2018.
 */
@Dao
abstract class TaskDao {
  @Query("SELECT * FROM Task WHERE tid = :tid")
  abstract fun getById(tid: String): Task

  @Query("SELECT * FROM Task WHERE owner_uid = :uid")
  abstract fun getByOwner(uid: String): List<Task>

  @Query("SELECT * FROM Task WHERE owner_uid = :uid AND status IN (:status)")
  abstract fun getByOwner(uid: String, vararg status: String): List<Task>

  @Query("SELECT * FROM Task")
  abstract fun get(): List<Task>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun save(task: Task)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun save(tasks: List<Task>)
}