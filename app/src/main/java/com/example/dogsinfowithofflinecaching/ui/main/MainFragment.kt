package com.example.dogsinfowithofflinecaching.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dogsinfowithofflinecaching.R
import com.example.dogsinfowithofflinecaching.adapter.DogsAdapter
import com.example.dogsinfowithofflinecaching.adapter.LoadingStateAdapter
import com.example.dogsinfowithofflinecaching.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val homeViewModel: MainViewModel by viewModels()
    private var _binding: MainFragmentBinding? = null

    @Inject
    lateinit var dogsAdapter : DogsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initRecyclerView()

        lifecycleScope.launchWhenStarted {
            homeViewModel.getAllDogsInfo().collectLatest { response->
                binding.apply {
                    recyclerview.isVisible = true
                    progressBar.isVisible = false
                }
                dogsAdapter.submitData(response)
            }
        }
        return root
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(),2)
                adapter = dogsAdapter.withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter{dogsAdapter.retry()},
                    footer = LoadingStateAdapter{dogsAdapter.retry()}
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}