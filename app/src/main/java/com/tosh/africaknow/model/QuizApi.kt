package com.tosh.africaknow.model

import io.reactivex.Single
import retrofit2.http.GET

interface QuizApi {

    @GET("/quizes")
    fun getQuestions(): Single<Quiz>
}