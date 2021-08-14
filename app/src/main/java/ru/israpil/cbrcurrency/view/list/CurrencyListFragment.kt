package ru.israpil.cbrcurrency.view.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.israpil.cbrcurrency.R
import ru.israpil.cbrcurrency.databinding.FragmentCurrencyListBinding
import ru.israpil.cbrcurrency.view.base.ViewState
import ru.israpil.cbrcurrency.view.list.recycler.CurrencyAdapter

@AndroidEntryPoint
class CurrencyListFragment : Fragment(R.layout.fragment_currency_list) {
    private val viewBinding: FragmentCurrencyListBinding
            by viewBinding(FragmentCurrencyListBinding::bind)
    private val viewModel: CurrencyListViewModel by viewModels()

    private val currencyListAdapter = CurrencyAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.currenciesState.observe(viewLifecycleOwner) {
            viewBinding.rvCurrencies.isVisible = it is ViewState.Show
            viewBinding.tvError.isVisible = it !is ViewState.Show
            if (it is ViewState.Show) currencyListAdapter.submitList(it.data)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            viewBinding.cpiLoading.isVisible = it
        }
        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            viewBinding.srlCurrencies.isRefreshing = it
        }
    }

    private fun initViews() {
        viewBinding.rvCurrencies.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rvCurrencies.adapter = currencyListAdapter
        viewBinding.srlCurrencies.setOnRefreshListener { viewModel.onCurrenciesRefresh() }
    }
}
