package com.surya.mvvmstarter.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.surya.mvvmstarter.data.repository.UserRepository
import com.surya.mvvmstarter.util.ApiException
import com.surya.mvvmstarter.util.Coroutines
import com.surya.mvvmstarter.util.NoInternetException

/**
 * Created by suryamudti on 03/08/2019.
 */
class AuthViewModel(
    private val repository: UserRepository

) : ViewModel() {
    var email: String ? = null
    var password: String ? = null

    var authListener : AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View){

        Log.i("Datas","email $email pass $password")

        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("invalid email or password !!")

            return

        }

        Coroutines.main{
            try {
                val authResponse = repository.userLogin(email!!,password!!)
                authResponse.user?.let {

                    authListener?.onSuccess(it)
                    repository.saveUser(it)

                    return@main
                }

                authListener?.onFailure(authResponse.message!!)
            }catch (e: ApiException){
                authListener?.onFailure(e.message!!)

            }catch (e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }
        }

    }
}