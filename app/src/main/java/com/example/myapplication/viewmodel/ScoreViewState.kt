package com.example.myapplication.viewmodel

//TODO if given more time I would also have a ScoreViewEvent class to be able to navigate back to home screen
//TODO if given more time I would make the variables integers for being able to do math on the scores
data class ScoreViewState (
    val schoolName: String,
    val mathScore: String,
    val readingScore: String,
    val writingScore: String
)