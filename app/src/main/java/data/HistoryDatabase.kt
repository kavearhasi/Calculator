package data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [History::class], version = 1, exportSchema = false)
@TypeConverters(Convertor::class)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao() : HistoryDao
    companion object {
        private var INSTANCE: HistoryDatabase? = null
        fun getDatabase(context: Context): HistoryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDatabase::class.java,
                    "Calculator"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }
}