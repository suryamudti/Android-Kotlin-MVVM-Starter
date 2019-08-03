package com.surya.mvvmstarter.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surya.mvvmstarter.data.repository.QuotesRepository
import com.surya.mvvmstarter.data.repository.UserRepository

/**
 * Created by suryamudti on 03/08/2019.
 */

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(
    private val repository: QuotesRepository

) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
    }
}