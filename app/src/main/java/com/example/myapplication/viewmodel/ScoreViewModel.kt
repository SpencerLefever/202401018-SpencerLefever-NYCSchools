package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.baselivedata.LiveEvent
import com.example.myapplication.baselivedata.MutableLiveEvent
import com.example.myapplication.baselivedata.emit
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreViewModel @Inject constructor() : ViewModel() {

    private val _viewState = MutableLiveEvent<ScoreViewState>()

    val viewState: LiveEvent<ScoreViewState> get() = _viewState

    fun emitInitialViewState(viewState: ScoreViewState) {
        _viewState.emit(viewState)
    }
}