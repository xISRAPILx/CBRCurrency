package ru.israpil.cbrcurrency.view.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.israpil.cbrcurrency.R
import ru.israpil.cbrcurrency.domain.currency.model.Currency
import ru.israpil.cbrcurrency.view.base.ViewState
import kotlin.math.max

@Composable
fun CurrencyListScreen(viewModel: CurrencyListViewModel = viewModel()) {
    val isLoading by viewModel.isLoading.observeAsState(true)
    if (isLoading) {
        ContentLoadingIndicator()
    } else {
        val currencies by viewModel.currenciesState.observeAsState(ViewState.Empty)
        when (currencies) {
            ViewState.Empty -> CurrenciesLoadingError()
            is ViewState.Error -> CurrenciesLoadingError()
            is ViewState.Show -> CurrenciesList(currencies = (currencies as ViewState.Show<List<Currency>>).data)
        }
    }
}

@Composable
private fun CurrenciesList(currencies: List<Currency>) {
    LazyColumn {
        items(currencies) { currency ->
            CurrencyItem(currency = currency)
        }
    }
}

@Composable
private fun CurrencyItem(currency: Currency) {
    Row(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.currency_item_padding))
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.currency_code_background),
                    shape = CircleShape
                )
                .square()
        ) {
            Text(
                text = currency.charCode,
                color = colorResource(id = R.color.white),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.currency_code_padding))
            )
        }
        Column(
            Modifier
                .weight(1f)
                .padding(
                    start = dimensionResource(id = R.dimen.margin_medium),
                    end = dimensionResource(id = R.dimen.margin_medium)
                )
        ) {
            Text(text = currency.name, overflow = TextOverflow.Ellipsis, maxLines = 1)
            Row {
                Image(
                    painter = painterResource(
                        id = if (currency.value > currency.prevValue)
                            R.drawable.ic_baseline_trending_up_24
                        else R.drawable.ic_baseline_trending_down_24
                    ),
                    contentDescription = stringResource(
                        id = if (currency.value > currency.prevValue)
                            R.string.currency_up else R.string.currency_down
                    ),
                    colorFilter = ColorFilter.tint(colorResource(id = R.color.currency_code_background)),
                )
                Text(
                    text = stringResource(
                        id = R.string.currency_value_mask,
                        currency.value.toPlainString()
                    ),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_small))
                )
                Text(
                    text = stringResource(
                        id = R.string.currency_value_mask,
                        currency.prevValue.toPlainString()
                    ),
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.margin_medium))
                )
            }
        }
    }
}

@Composable
private fun CurrenciesLoadingError() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.currencies_loading_error))
    }
}

@Composable
private fun ContentLoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

private fun Modifier.square() = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    val size = max(placeable.height, placeable.width)
    layout(size, size) {
        placeable.placeRelative(
            (size - placeable.width) / 2,
            (size - placeable.height) / 2
        )
    }
}
