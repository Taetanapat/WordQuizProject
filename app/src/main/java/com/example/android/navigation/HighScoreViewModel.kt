package com.example.android.navigation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.navigation.database.PlayerDataModel
import com.example.android.navigation.database.PlayerDAO
import com.example.android.navigation.database.PlayerDatabase
import kotlinx.coroutines.*

class HighScoreViewModel (application: Application): AndroidViewModel(application){ //
// private var viewModelJob = Job()
//    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
 //   private  val players = database.getAllPlayer()
//    private var tonight = MutableLiveData<PlayerDataModel?>()




    private val repository: InsertScoreRepository
    val all: LiveData<List<PlayerDataModel>>



    init {
        val wordsDao = PlayerDatabase.getInstance(application.applicationContext).Player()
        repository = InsertScoreRepository(wordsDao)
        all = repository.allWords

    }
    fun get() = viewModelScope.launch {
        repository.get()
    }
    fun getAll() = viewModelScope.launch {
       repository.getAll()
    }

    fun insert(word: PlayerDataModel) = viewModelScope.launch {
        repository.insert(word)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }


}