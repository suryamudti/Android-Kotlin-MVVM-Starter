package com.surya.mvvmstarter.ui.home.quotes

import android.content.Intent
import android.util.Log
import com.surya.mvvmstarter.R
import com.surya.mvvmstarter.data.db.entities.Quote
import com.surya.mvvmstarter.databinding.ItemQuoteBinding
import com.surya.mvvmstarter.ui.home.quotes.quotes_detail.QuotesDetailActivity
import com.xwray.groupie.databinding.BindableItem

/**
 * Created by suryamudti on 04/08/2019.
 */
class QuotesItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.root.setOnClickListener {

            val intent = Intent(viewBinding.root.context, QuotesDetailActivity::class.java)
            intent.putExtra("quote",quote)
            it.context.startActivity(intent)

        }
        viewBinding.setQuote(quote)
    }
}