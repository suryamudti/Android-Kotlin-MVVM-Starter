package com.surya.mvvmstarter.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surya.mvvmstarter.data.db.entities.Quote

/**
 * Created by suryamudti on 04/08/2019.
 */

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(quotes: List<Quote>)

    @Query("SELECT * FROM Quote")
    fun getQuotes() : LiveData<List<Quote>>
}