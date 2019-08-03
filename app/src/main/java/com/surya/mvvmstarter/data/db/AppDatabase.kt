package com.surya.mvvmstarter.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.surya.mvvmstarter.data.db.dao.QuoteDao
import com.surya.mvvmstarter.data.db.dao.UserDao
import com.surya.mvvmstarter.data.db.entities.Quote
import com.surya.mvvmstarter.data.db.entities.User

/**
 * Created by suryamudti on 03/08/2019.
 */

@Database(
    entities = [User::class, Quote::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao
    abstract fun getQuoteDao() : QuoteDao

    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "starter.db"
            ).build()
    }
}