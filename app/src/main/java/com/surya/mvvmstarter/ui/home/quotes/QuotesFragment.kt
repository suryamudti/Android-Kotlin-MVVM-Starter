package com.surya.mvvmstarter.ui.home.quotes

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.surya.mvvmstarter.R
import com.surya.mvvmstarter.data.db.entities.Quote
import com.surya.mvvmstarter.ui.home.quotes.quotes_detail.QuotesDetailActivity
import com.surya.mvvmstarter.util.Coroutines
import com.surya.mvvmstarter.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quote_fragment.*
import org.kodein.di.android.x.kodein

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory : QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quote_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        viewModel.quotes.await().observe(this, Observer {
            initRecycleView(it.toQuoteItem())
        })
    }

    private fun initRecycleView(quotesItem: List<QuotesItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quotesItem)
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Quote>.toQuoteItem() : List<QuotesItem>{
        return this.map {

            QuotesItem(it)
        }
    }

}
