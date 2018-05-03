package namnh.com.mvpkotlin.data.model

import android.arch.persistence.room.*
import android.support.annotation.NonNull
import android.support.annotation.StringDef
import com.google.gson.annotations.Expose
import java.util.*

/**
 * Created by namnh on 02/05/2018.
 */

@Entity(
  tableName = "task", foreignKeys =
  [(ForeignKey(
    entity = User::class, parentColumns = ["uid"],
    childColumns = ["owner_uid"],
    onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE
  )), (ForeignKey(
    entity = User::class, parentColumns = ["uid"],
    childColumns = ["reviewer_uid"],
    onUpdate = ForeignKey.CASCADE, onDelete = ForeignKey.CASCADE
  ))],
  indices = [Index(value = ["owner_uid"], unique = true), Index(
    value = ["reviewer_uid"],
    unique = true
  )]
)
data class Task(
  @ColumnInfo(name = "title") var title: String,
  @ColumnInfo(name = "description") var description: String,
  @Expose @ColumnInfo(name = "owner_uid") var ownerUid: String,
  @Expose @ColumnInfo(name = "reviewer_uid") @Status var reviewerUid: String,
  @Expose @ColumnInfo(name = "status") var status: String,
  @Expose @NonNull @PrimaryKey @ColumnInfo(
    name = "tid"
  ) var id: String = UUID.randomUUID().toString()
) {

  val titleForList: String
    get() = if (title.isNotEmpty()) title else description

  val isActive
    get() = status != TODO && status != DONE

  val isEmpty
    get() = title.isEmpty() && description.isEmpty()

  @StringDef(value = [TODO, IN_PROGRESS, RESOLVED, DONE, REVIEW, TESTING, REOPEN])
  annotation class Status

  companion object {
    const val TODO = "TODO"
    const val IN_PROGRESS = "IN_PROGRESS"
    const val RESOLVED = "RESOLVED"
    const val REVIEW = " REVIEW"
    const val TESTING = "TESTING"
    const val REOPEN = "REOPEN"
    const val DONE = "DONE"
  }
}
