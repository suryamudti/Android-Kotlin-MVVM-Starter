package com.surya.mvvmstarter.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.surya.mvvmstarter.data.repository.QuotesRepository
import com.surya.mvvmstarter.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {

        repository.getQuotes()
    }
}
