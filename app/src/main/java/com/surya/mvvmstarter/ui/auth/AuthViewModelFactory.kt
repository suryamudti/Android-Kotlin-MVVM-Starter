package com.surya.mvvmstarter.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surya.mvvmstarter.data.repository.UserRepository

/**
 * Created by suryamudti on 03/08/2019.
 */

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    val repository: UserRepository

) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}