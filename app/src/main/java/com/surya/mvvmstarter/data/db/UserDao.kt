package com.surya.mvvmstarter.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.surya.mvvmstarter.data.db.entities.CURRENT_USER_ID
import com.surya.mvvmstarter.data.db.entities.User

/**
 * Created by suryamudti on 03/08/2019.
 */

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: User): Long

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>


}