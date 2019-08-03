package com.surya.mvvmstarter.ui.auth

import android.content.Intent
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
    var name : String ? = null
    var email: String ? = null
    var password: String ? = null
    var passwordConfirm : String? = null

    var authListener : AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View){

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

    fun onSignupButtonClick(view: View){

        authListener?.onStarted()

        if (name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }

        if (email.isNullOrEmpty()){
            authListener?.onFailure("email is required")
            return
        }

        if (password.isNullOrEmpty()){
            authListener?.onFailure("password is required")
            return
        }

        if (password != passwordConfirm){
            authListener?.onFailure("please enter correct password")
            return
        }

        Coroutines.main{
            try {
                val authResponse = repository.userSignup(name!!, email!!,password!!)
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

    fun onSignup(view: View){
        Intent(view.context, SignupActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            view.context.startActivity(it)
        }

    }

    fun onLogin(view: View){
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}