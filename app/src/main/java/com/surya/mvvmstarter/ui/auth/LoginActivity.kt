package com.surya.mvvmstarter.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.surya.mvvmstarter.R
import com.surya.mvvmstarter.data.db.entities.User
import com.surya.mvvmstarter.databinding.ActivityLoginBinding
import com.surya.mvvmstarter.ui.home.HomeActivity
import com.surya.mvvmstarter.util.hide
import com.surya.mvvmstarter.util.show
import com.surya.mvvmstarter.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {
            user ->
            if (user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        progressBar.hide()
    }


    override fun onStarted() {
        progressBar.show()
        toast("login start bro")
    }

    override fun onSuccess(user: User) {
        toast("${user.name} is logged in")
        progressBar.hide()

    }

    override fun onFailure(message: String) {
        toast(message)
        progressBar.hide()
    }
}
