package namnh.com.mvpkotlin.data.source.local.api.converters

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by namnh on 02/05/2018.
 */
class DateMillisConverter {
  companion object {
    @TypeConverter
    fun millisToDate(millis: Long?): Date? = if (millis == null) null else Date(millis)

    @TypeConverter
    fun dateToMillis(date: Date?): Long? = date?.time
  }
}