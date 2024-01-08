package com.example.myapplication.router

import androidx.navigation.NavController
import com.example.myapplication.NavMainGraphDirections
import com.example.myapplication.viewmodel.SatData

class MainRouterImpl: MainRouter {
    override fun show(navController: NavController, satData: SatData) {
        navController.navigate(NavMainGraphDirections.actionToScoreFragment(satData))
    }


}