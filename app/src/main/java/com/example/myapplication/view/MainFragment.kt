package com.example.myapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.router.MainRouter
import com.example.myapplication.viewmodel.MainViewEvent
import com.example.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: Fragment() {
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var fragmentMainBinding: FragmentMainBinding

    @Inject
    lateinit var mainRouter: MainRouter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return fragmentMainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.emitInitialViewState(requireContext())

        with(fragmentMainBinding) {
            viewModel = mainViewModel
            viewState = viewModel?.viewState?.value?.peekContent()
            executePendingBindings()
        }


        mainViewModel.viewEvent.observe(viewLifecycleOwner) {
            when(it.getContentIfNotHandled()) {
                is MainViewEvent.GoToSchool -> {
                    navigateToSatScores()
                }
                else -> {}
            }
        }

        mainViewModel.viewState.observe(viewLifecycleOwner) {
            val viewState = it.peekContent()
            fragmentMainBinding.schoolRv.apply {
                val myAdapter = MainBindingAdapter(viewState)
                myAdapter.setOnClickListener(
                    object:
                        MainBindingAdapter.OnClickListener {
                        override fun onClick(position: Int) {
                            mainViewModel.schoolPressed(requireContext(), position)
                        }
                    }
                )
                adapter = myAdapter
            }

        }
    }

    private fun navigateToSatScores() {
        mainRouter.show(this.findNavController(), mainViewModel.currentSatData)
    }

}