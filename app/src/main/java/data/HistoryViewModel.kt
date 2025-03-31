package data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    val readAllHistory: LiveData<List<History>>
    private val repository: HistoryRepository

    init {
        val historyDao = HistoryDatabase.getDatabase(application).historyDao()
        repository = HistoryRepository(historyDao)
        readAllHistory = repository.allHistory
    }

    fun addHistory(history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHistory(history)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun delete(history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(history)
        }
    }


}