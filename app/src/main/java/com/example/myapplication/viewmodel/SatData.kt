package com.example.myapplication.viewmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SatData(
    val dbn: String,
    val school_name: String,
    val num_of_sat_test_takers: String,
    val sat_critical_reading_avg_score: String,
    val sat_math_avg_score: String,
    val sat_writing_avg_score: String
) : Parcelable
