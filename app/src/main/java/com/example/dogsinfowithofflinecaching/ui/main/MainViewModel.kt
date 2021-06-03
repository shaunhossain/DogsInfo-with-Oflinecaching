package com.example.dogsinfowithofflinecaching.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dogsinfowithofflinecaching.model.dogs.DogsItem
import com.example.dogsinfowithofflinecaching.network.ApiService
import com.example.dogsinfowithofflinecaching.repository.DogsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(private val apiService: ApiService): ViewModel() {
    fun getAllDogsInfo(): Flow<PagingData<DogsItem>> = Pager(
        config = PagingConfig(20, enablePlaceholders = false)
    ) {
        DogsPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}
