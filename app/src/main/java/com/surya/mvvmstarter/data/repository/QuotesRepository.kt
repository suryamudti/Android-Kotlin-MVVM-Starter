package com.surya.mvvmstarter.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.surya.mvvmstarter.data.db.AppDatabase
import com.surya.mvvmstarter.data.db.entities.Quote
import com.surya.mvvmstarter.data.network.MyApi
import com.surya.mvvmstarter.data.network.SafeApiRequest
import com.surya.mvvmstarter.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by suryamudti on 04/08/2019.
 */
class QuotesRepository(
    private val api : MyApi,
    private val db : AppDatabase
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private suspend fun fetchQuotes(){
        if (isFetchNeeded()){
            val response = apiRequest {
                api.getQuotes()
            }
            quotes.postValue(response.quotes)
        }
    }

    suspend fun getQuotes() : LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    fun isFetchNeeded(): Boolean{
        return true
    }

    private fun saveQuotes(quotes : List<Quote>){
        Coroutines.io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}