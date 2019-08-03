package com.surya.mvvmstarter.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by suryamudti on 04/08/2019.
 */

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val quote: String,
    val author: String,
    val thumbnail: String,
    val created_at: String,
    val updated_at: String
) {
}