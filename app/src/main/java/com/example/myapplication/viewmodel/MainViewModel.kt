package com.example.myapplication.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.baselivedata.LiveEvent
import com.example.myapplication.baselivedata.MutableLiveEvent
import com.example.myapplication.baselivedata.emit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.BufferedReader
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel() {

    private val _viewState = MutableLiveEvent<MainViewState>()

    val viewState: LiveEvent<MainViewState> get() = _viewState

    private val _viewEvent = MutableLiveEvent<MainViewEvent>()

    val viewEvent: LiveEvent<MainViewEvent> get() = _viewEvent

    lateinit var currentSatData: SatData

    fun emitInitialViewState(context: Context) {
        _viewState.emit(
            MainViewState(
                getSchoolName(context)
            )
        )
    }

    private fun getSchoolName(context: Context): List<SchoolData> {
        val gson = Gson()
        val jsonData: String = context.assets.open("SchoolData.json").bufferedReader().use { it.readText() }
        val data = gson.fromJson(jsonData, Array<SchoolData>::class.java)
        //TODO For sake of having small rv only add first 20 schools
        val nameData = mutableListOf<SchoolData>()
        for(i in 0..20) {
            nameData.add(data[i])
        }
        return nameData.toList()
    }

    private fun getCurrentSatData(context: Context, currentSchoolDbn: String?): SatData {
        //TODO In some cases the app will crash  because the school is not in the sat score data base band aid would be a null check
        //TODO Future proof would be to check against the score database to ensure the school is included
        val gson = Gson()
        val jsonData: String = context.assets.open("SatData.json").bufferedReader().use { it.readText() }
        val data = gson.fromJson(jsonData, Array<SatData>::class.java)

        return  data.first { it.dbn == currentSchoolDbn }
    }

    fun schoolPressed(context: Context, position: Int) {
        currentSatData = getCurrentSatData(context, viewState.value?.peekContent()?.schools?.get(position)?.dbn)
        _viewEvent.emit(MainViewEvent.GoToSchool)
    }
}