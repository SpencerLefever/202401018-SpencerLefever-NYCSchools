package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentScoreBinding
import com.example.myapplication.viewmodel.ScoreViewModel
import com.example.myapplication.viewmodel.ScoreViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreFragment: Fragment() {
    private val scoreViewModel: ScoreViewModel by viewModels()

    private val args: ScoreFragmentArgs by navArgs()

    private lateinit var fragmentScoreBinding: FragmentScoreBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentScoreBinding = FragmentScoreBinding.inflate(inflater, container, false)

        return fragmentScoreBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scoreData = args.schoolKey

        scoreViewModel.emitInitialViewState(
            ScoreViewState(
                scoreData.school_name,
                scoreData.sat_math_avg_score,
                scoreData.sat_critical_reading_avg_score,
                scoreData.sat_writing_avg_score
            )
        )

        with(fragmentScoreBinding) {
            viewModel = scoreViewModel
            viewState = scoreViewModel.viewState.value?.peekContent()
            executePendingBindings()
        }
    }

    //TODO If given more time I would add routing a view events to navigate back to home screen

}