package com.tosh.africaknow.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_table")
data class ScoreEntity(
    @PrimaryKey
    val id:Int,
    val score: Int,
    val questionNum: Int
)