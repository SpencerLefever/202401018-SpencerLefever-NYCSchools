package com.example.myapplication.router

import androidx.navigation.NavController
import com.example.myapplication.viewmodel.SatData

interface MainRouter {

    fun show(navController: NavController, satData: SatData)
}