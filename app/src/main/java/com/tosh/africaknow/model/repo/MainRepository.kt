package com.tosh.africaknow.model.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.tosh.africaknow.model.room.MainDatabase
import com.tosh.africaknow.model.room.ScoreEntityDao
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainRepository(application: Application) {

    private var scoreDao: ScoreEntityDao

    init {
        val mainDb: MainDatabase = MainDatabase.getInstance(
            application.applicationContext
        )!!

        scoreDao = mainDb.scoreDao()
    }

    suspend fun getScore(): Int {
        return coroutineScope{
            scoreDao.getUserScore()
        }
    }

    suspend fun getQuestionNum(): Int {
        return coroutineScope{
            scoreDao.getQuestionNumber()
        }
    }

    suspend fun insertScore(score:Int){
        coroutineScope{
            launch {
                scoreDao.updateScore(score)
            }
        }
    }

   suspend fun updateQuestionNum(num:Int){
        coroutineScope{
            launch {
                scoreDao.updateQuestionNumber(num)
            }
        }
    }

    suspend fun insertQuestionNum(score:Int, num: Int){
        coroutineScope{
            launch {
                scoreDao.insertQuestionNumber(score, num)
            }
        }
    }
}