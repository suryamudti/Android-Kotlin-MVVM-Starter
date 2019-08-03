package com.surya.mvvmstarter

import android.app.Application
import com.surya.mvvmstarter.data.db.AppDatabase
import com.surya.mvvmstarter.data.network.MyApi
import com.surya.mvvmstarter.data.network.NetworkConnectionInterceptor
import com.surya.mvvmstarter.data.repository.QuotesRepository
import com.surya.mvvmstarter.data.repository.UserRepository
import com.surya.mvvmstarter.ui.auth.AuthViewModel
import com.surya.mvvmstarter.ui.auth.AuthViewModelFactory
import com.surya.mvvmstarter.ui.home.profile.ProfileViewModelFactory
import com.surya.mvvmstarter.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by suryamudti on 03/08/2019.
 */
class MVVMApplication : Application(

), KodeinAware{
    override val kodein = Kodein.lazy{

        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(),instance()) }
        bind() from singleton { QuotesRepository(instance(),instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }




    }

}