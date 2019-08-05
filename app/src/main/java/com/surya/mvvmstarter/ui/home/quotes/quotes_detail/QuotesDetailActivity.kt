package com.surya.mvvmstarter.ui.home.quotes.quotes_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.surya.mvvmstarter.R
import com.surya.mvvmstarter.data.db.entities.Quote
import com.surya.mvvmstarter.databinding.ActivityQuotesDetailBinding

class QuotesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityQuotesDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_quotes_detail)
        val quote = intent.getParcelableExtra<Quote>("quote")

        binding.quote = quote


    }
}
