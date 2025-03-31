package data

import androidx.lifecycle.LiveData


 class HistoryRepository(private val historyDao: HistoryDao) {
    val allHistory: LiveData<List<History>> = historyDao.getAllHistory()

    suspend fun addHistory(history: History) {
        historyDao.add(history)
    }

     suspend fun deleteAll(){
         historyDao.cleanHistory()
     }
     suspend fun delete(history: History){
         historyDao.delete(history)
     }

}