package com.surya.mvvmstarter.data.repository

import com.surya.mvvmstarter.data.db.AppDatabase
import com.surya.mvvmstarter.data.db.entities.User
import com.surya.mvvmstarter.data.network.MyApi
import com.surya.mvvmstarter.data.network.SafeApiRequest
import com.surya.mvvmstarter.data.network.responses.AuthResponse
import retrofit2.Response

/**
 * Created by suryamudti on 03/08/2019.
 */
class UserRepository (
    private val api: MyApi,
    private val db : AppDatabase
): SafeApiRequest(){

    suspend fun userLogin(email: String, password: String): AuthResponse{

        return apiRequest {
            api.userLogin(email,password)
        }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()



}