package namnh.com.mvpkotlin.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.util.*

/**
 * Created by namnh on 02/05/2018.
 */
@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "email") var email: String,
    @NonNull @PrimaryKey @ColumnInfo(
        name = "uid") var uid: String = UUID.randomUUID().toString()
)