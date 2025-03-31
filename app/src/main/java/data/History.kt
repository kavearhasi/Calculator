package data

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "history_table")

data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val expression: String,
    val result: Double,
    val date: String
)
