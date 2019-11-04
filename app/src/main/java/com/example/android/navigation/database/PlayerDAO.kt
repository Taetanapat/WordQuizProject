package com.example.android.navigation.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface PlayerDAO {
    @Insert
    fun insert(player: Player)

    @Update
    fun update(player: Player)

    @Query("SELECT * from score_table WHERE PlayerID = :key")
    fun get(key: Long): Player?

    @Query("DELETE FROM score_table")
    fun clear()

    @Query("SELECT * FROM score_table ORDER BY PlayerID DESC LIMIT 1")
    fun getPlayer(): Player?

    @Query("SELECT * FROM score_table ORDER BY PlayerID DESC")
    fun getAllPlayer(): LiveData<List<Player>>
}