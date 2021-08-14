package ru.israpil.cbrcurrency.view.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.israpil.cbrcurrency.R
import ru.israpil.cbrcurrency.domain.currency.model.Currency

class CurrencyAdapter : ListAdapter<Currency, CurrencyViewHolder>(CurrencyDiffItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency, parent, false)
        return CurrencyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
