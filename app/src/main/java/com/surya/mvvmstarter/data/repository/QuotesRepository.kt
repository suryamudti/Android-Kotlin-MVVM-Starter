package com.surya.mvvmstarter.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.surya.mvvmstarter.data.db.AppDatabase
import com.surya.mvvmstarter.data.db.entities.Quote
import com.surya.mvvmstarter.data.network.MyApi
import com.surya.mvvmstarter.data.network.SafeApiRequest
import com.surya.mvvmstarter.data.preferences.PreferencesProvider
import com.surya.mvvmstarter.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * Created by suryamudti on 04/08/2019.
 */

private val MINIMUM_INTERVAL = 6
class QuotesRepository(
    private val api : MyApi,
    private val db : AppDatabase,
    private val prefs :  PreferencesProvider
) : SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {

        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private suspend fun fetchQuotes(){

        val lastSavedAt = prefs.getLastSavedAt()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun isFetchNeeded(saveAt: LocalDateTime): Boolean{
        return ChronoUnit.HOURS.between(saveAt,LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes : List<Quote>){
        Coroutines.io {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                prefs.saveLastSavedAt(LocalDateTime.now().toString())
            }
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}