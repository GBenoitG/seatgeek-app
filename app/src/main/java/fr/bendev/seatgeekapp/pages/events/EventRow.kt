package fr.bendev.seatgeekapp.pages.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import fr.bendev.seatgeekapp.R
import fr.bendev.seatgeekapp.theme.SeatGeekTheme
import fr.bendev.seatgeekapp.utils.DateFormatHelper
import java.util.Date

@Composable
fun EventRow(
    name: String,
    date: Date,
    price: Float?,
    country: String,
    city: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = dimensionResource(id = R.dimen.size_xsmall)
        ),
    ) {
        val context = LocalContext.current
        Column(
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .padding(dimensionResource(id = R.dimen.size_medium))
        ) {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(text = "$country, $city", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_xxlarge)))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = DateFormatHelper.formatFullDateWithTime(context, date),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                val priceString = price?.let {
                    stringResource(id = R.string.common_amount_money, it)
                } ?: stringResource(id = R.string.common_amount_free)
                Text(
                    text = priceString,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview(name = "Default", widthDp = 360, heightDp = 640)
@Composable
private fun DefaultPreview() {
    SeatGeekTheme {
        EventRow(
            name = "Event",
            date = Date(),
            price = 12.3f,
            country = "France",
            city = "Lyon",
            {})
    }
}