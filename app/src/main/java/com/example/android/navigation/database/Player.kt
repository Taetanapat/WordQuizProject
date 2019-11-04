package com.example.android.navigation.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class Player(
        @PrimaryKey(autoGenerate = true)
        var PlayerID: Long = 0L,

        @ColumnInfo(name = "name")
        var PlayerName: String,

        @ColumnInfo(name = "score")
        var PlayerScore: String


)