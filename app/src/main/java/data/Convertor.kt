package data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime

class Convertor {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }
}