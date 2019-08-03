package com.surya.mvvmstarter.ui.auth

import androidx.lifecycle.LiveData
import com.surya.mvvmstarter.data.db.entities.User

/**
 * Created by suryamudti on 03/08/2019.
 */
interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}