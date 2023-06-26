package fr.bendev.seatgeekapp.pages.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
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
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = dimensionResource(id = R.dimen.size_xsmall)
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        val context = LocalContext.current
        Column(
            Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.size_medium))
        ) {
            Text(text = name, style = MaterialTheme.typography.titleMedium)
            Text(text = "$country, $city", style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_xxlarge)))
            Text(
                text = DateFormatHelper.formatFullDate(context, date),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(name = "Default", widthDp = 360, heightDp = 640)
@Composable
private fun DefaultPreview() {
    SeatGeekTheme {
        EventRow(name = "Event", date = Date(), price = 12.3f, country = "France", city = "Lyon")
    }
}