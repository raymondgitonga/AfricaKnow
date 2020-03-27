package com.tosh.africaknow.model

data class Quiz(
    val message: String,
    val questions: List<Question>
)

data class Question(
    val answers: List<Answer>,
    val question: String
)

data class Answer(
    val option: String,
    val value: Boolean
)