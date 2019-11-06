package com.example.android.navigation

import androidx.lifecycle.LiveData
import com.example.android.navigation.database.PlayerDataModel
import com.example.android.navigation.database.PlayerDAO


class InsertScoreRepository(private val playerDao: PlayerDAO) {

        // Room executes all queries on a separate thread.
        // Observed LiveData will notify the observer when the data has changed.
        val allWords: LiveData<List<PlayerDataModel>> = playerDao.get()

        fun insert(playerDataModel: PlayerDataModel){
            playerDao.insert(playerDataModel)
        }

        fun get(){
        playerDao.get()
        }

        fun getAll(){
            playerDao.getAllPlayer()
        }
        fun clear(){
            playerDao.clear()
        }


    }
