package com.tosh.africaknow.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tosh.africaknow.R
import com.tosh.africaknow.model.Question
import com.tosh.africaknow.model.QuizService
import com.tosh.africaknow.model.repo.MainRepository
import com.tosh.africaknow.view.activity.MainActivity
import com.tosh.africaknow.view.fragment.GameOverFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): BaseViewModel(application) {

    private val disposable = CompositeDisposable()
    private val quizService = QuizService()
    private val repo = MainRepository(application)
    val activity = MainActivity()

    fun fetchQuestions(position: Int):MutableLiveData<Question>{
        val question: MutableLiveData<Question> = MutableLiveData()

        disposable.add(
            quizService.getQuiz()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {quiz ->
                        question.value = quiz.questions[position]
                    },
                    {error->
                        Log.e("ViewModelError", " ${error.localizedMessage}")
                    }
                )
        )
        return question
    }

    fun updateQuestionNum(questionNum: Int){
        launch {
            repo.updateQuestionNum(questionNum)
        }
    }

    fun insertQuestionNum(score: Int,questionNum: Int){
        launch {
            repo.insertQuestionNum(score, questionNum)
        }
    }

    fun getUpdateScore(score: Int){
        launch {
            repo.insertScore(score)
        }
    }

    fun getScore(): MutableLiveData<Int>{
        var score: MutableLiveData<Int> = MutableLiveData()
        launch {
            score.value = repo.getScore()
        }

        return score
    }

    fun getQuestionNum(): MutableLiveData<Int>{
        var questionNum: MutableLiveData<Int> = MutableLiveData()
        launch {
            questionNum.value = repo.getQuestionNum()
        }

        return questionNum
    }

    override fun onCleared() {
        disposable.clear()
    }
}