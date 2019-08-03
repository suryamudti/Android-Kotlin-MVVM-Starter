package com.surya.mvvmstarter.data.network.responses

import com.surya.mvvmstarter.data.db.entities.User

/**
 * Created by suryamudti on 03/08/2019.
 */
data class AuthResponse(
    val isSuccessful: Boolean?,
    val message : String?,
    val user : User?
)