package fr.bendev.seatgeekapp.pages.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.bendev.seatgeekapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    title: String,
    viewModel: EventsViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title, style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) {
        LazyColumn(
            Modifier
                .padding(it)
                .padding(dimensionResource(id = R.dimen.size_medium))
        ) {
            item {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_small)))
            }
            itemsIndexed(uiState.events) { index, item ->
                EventRow(
                    name = item.title,
                    date = item.date,
                    price = item.stats.averagePrice,
                    country = item.location.country,
                    city = item.location.city,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(Color.White)
                )
                if (index < uiState.events.lastIndex) {
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_medium)))
                }
            }
            item {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_medium)))
            }
        }
    }
}