package com.tosh.africaknow.model.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ScoreEntityDao {
    @Query("SELECT score FROM score_table")
    suspend fun getUserScore():Int

    @Query("UPDATE score_table SET score = :newScore")
    suspend fun updateScore(newScore:Int)

    @Query("SELECT questionNum FROM score_table WHERE id = 1")
    suspend fun getQuestionNumber():Int

    @Query("UPDATE score_table SET questionNum = :newQuestionNum WHERE id = 1")
    suspend fun updateQuestionNumber(newQuestionNum:Int)

    @Query("INSERT INTO score_table(score, questionNum) VALUES(:score, :newQuestionNum)")
    suspend fun insertQuestionNumber(score:Int, newQuestionNum:Int)

}