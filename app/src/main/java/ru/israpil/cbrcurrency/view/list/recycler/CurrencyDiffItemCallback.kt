package ru.israpil.cbrcurrency.view.list.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.israpil.cbrcurrency.domain.currency.model.Currency

class CurrencyDiffItemCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
        oldItem.value == newItem.value
}
