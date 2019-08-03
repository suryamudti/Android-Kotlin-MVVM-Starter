package com.surya.mvvmstarter.data.network.responses

import com.surya.mvvmstarter.data.db.entities.Quote

/**
 * Created by suryamudti on 04/08/2019.
 */
data class QuotesResponse(
    val isSuccessfull : Boolean,
    val quotes : List<Quote>
) {
}