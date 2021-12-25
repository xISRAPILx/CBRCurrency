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
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.israpil.cbrcurrency.R
import ru.israpil.cbrcurrency.domain.currency.model.Currency
import ru.israpil.cbrcurrency.domain.currency.model.CurrencyList
import ru.israpil.cbrcurrency.view.base.ViewState

@Composable
fun CurrencyListScreen(viewModel: CurrencyListViewModel = viewModel()) {
    val currencies by viewModel.currenciesState.collectAsState()
    when (currencies) {
        ViewState.Loading -> ContentLoadingIndicator()
        ViewState.Empty -> CurrenciesLoadingError()
        is ViewState.Error -> CurrenciesLoadingError()
        is ViewState.Show -> CurrenciesList(
            currencies = (currencies as ViewState.Show<CurrencyList>).data,
            isRefreshing = ((currencies as ViewState.Show<CurrencyList>).isRefreshing),
            onRefresh = { viewModel.onRefresh() }
        )
    }
}

@Composable
private fun CurrenciesList(
    currencies: CurrencyList,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = onRefresh
    ) {
        LazyColumn {
            items(currencies, key = { it.id }) { currency ->
                CurrencyItem(currency = currency)
            }
        }
    }
}

@Composable
private fun CurrencyItem(currency: Currency) {
    Row(
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.currency_item_padding))
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = Color.Red,
                    shape = CircleShape
                )
                .size(size = dpSize(48, 48))
        ) {
            Text(
                text = currency.charCode,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        Column(
            Modifier
                .weight(weight = 1f)
                .padding(horizontal = dimensionResource(id = R.dimen.margin_medium))
        ) {
            Text(text = currency.name, overflow = TextOverflow.Ellipsis, maxLines = 1)
            Row {
                Image(
                    painter = painterResource(
                        id = if (currency.up)
                            R.drawable.ic_baseline_trending_up_24
                        else R.drawable.ic_baseline_trending_down_24
                    ),
                    contentDescription = stringResource(
                        id = if (currency.up) R.string.currency_up else R.string.currency_down
                    ),
                    colorFilter = ColorFilter.tint(Color.Red),
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

@Immutable
data class DpSize(
    val width: Dp,
    val height: Dp
)

@Composable
fun dpSize(width: Int, height: Int) = DpSize(width = width.dp, height = height.dp)

fun Modifier.size(size: DpSize) = size(width = size.width, height = size.height)
