package data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(history: History)

    @Query("select * from history_table order by id desc")
    fun getAllHistory(): LiveData<List<History>>

    @Query("Delete from history_table")
    suspend fun cleanHistory()

    @Delete
    suspend fun delete(history: History)
}