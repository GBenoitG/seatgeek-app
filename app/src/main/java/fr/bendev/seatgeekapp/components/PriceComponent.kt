package fr.bendev.seatgeekapp.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.bendev.seatgeekapp.R

@Composable
fun PriceComponent(
    price: Float?,
    modifier: Modifier = Modifier
) {
    val priceString = price?.let {
        stringResource(id = R.string.common_amount_money, it)
    } ?: stringResource(id = R.string.common_amount_free)
    Text(
        text = priceString,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}

@Preview(name = "Default")
@Composable
private fun DefaultPreview() {
    MaterialTheme {
        PriceComponent(price = 123f)
    }
}

@Preview(name = "Null")
@Composable
private fun NullPreview() {
    MaterialTheme {
        PriceComponent(price = null)
    }
}