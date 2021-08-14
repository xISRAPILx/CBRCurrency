package ru.israpil.cbrcurrency.view.list.recycler

import android.text.Spannable
import android.text.style.StrikethroughSpan
import android.view.View
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.israpil.cbrcurrency.R
import ru.israpil.cbrcurrency.databinding.ItemCurrencyBinding
import ru.israpil.cbrcurrency.domain.currency.model.Currency

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val viewBinding: ItemCurrencyBinding by viewBinding(ItemCurrencyBinding::bind)
    fun bind(currency: Currency) {
        val context = viewBinding.root.context

        viewBinding.tvCurrencyCode.text = currency.charCode
        viewBinding.tvCurrencyName.text = currency.name
        viewBinding.tvCurrencyValue.text = context
            .getString(R.string.currency_value_mask, currency.value.toPlainString())
        viewBinding.tvCurrencyPrevValue.text = context
            .getString(R.string.currency_value_mask, currency.prevValue.toPlainString())
            .toSpannable()
            .apply {
                setSpan(StrikethroughSpan(), 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        viewBinding.tvCurrencyValue.setCompoundDrawablesWithIntrinsicBounds(
            if (currency.value > currency.prevValue) R.drawable.ic_baseline_trending_up_24
            else R.drawable.ic_baseline_trending_down_24,
            0,
            0,
            0
        )
    }
}
