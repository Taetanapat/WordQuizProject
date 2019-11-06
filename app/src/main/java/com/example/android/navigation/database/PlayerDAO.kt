package com.example.android.navigation.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface PlayerDAO {


    @Insert
    fun insert(playerDataModel: PlayerDataModel)

    @Update
    fun update(playerDataModel: PlayerDataModel)

    @Query("SELECT * from score_table ORDER BY score DESC")
    fun get(): LiveData<List<PlayerDataModel>>


    @Query("DELETE FROM score_table")
    fun clear()



    @Query("SELECT * FROM score_table ORDER BY score DESC")
    fun getAllPlayer(): LiveData<List<PlayerDataModel>>
}