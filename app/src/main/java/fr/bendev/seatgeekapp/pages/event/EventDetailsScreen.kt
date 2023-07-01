package fr.bendev.seatgeekapp.pages.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import fr.bendev.seatgeekapp.R
import fr.bendev.seatgeekapp.base.BaseViewModelFactory
import fr.bendev.seatgeekapp.components.PriceComponent
import fr.bendev.seatgeekapp.domain.model.Event
import fr.bendev.seatgeekapp.domain.model.Location
import fr.bendev.seatgeekapp.utils.DateFormatHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailsScreen(
    id: Long,
    title: String,
    onNavigateBack: () -> Unit,
    viewModel: EventDetailsViewModel = viewModel(factory = BaseViewModelFactory {
        EventDetailsViewModel(id)
    })
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                )
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Navigate Back",
                    Modifier.clickable {
                        onNavigateBack()
                    }
                )
            },
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = dimensionResource(id = R.dimen.size_medium)),
        ) {
            uiState.event?.let { event ->

                event.imageUrl?.let { imageUrl ->
                    Image(
                        painter = rememberAsyncImagePainter(model = imageUrl),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.size_xlarge))),
                        contentScale = ContentScale.FillWidth,
                    )
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_large)))

                TitleSection(event)

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_large)))

                PriceComponent(price = event.stats.averagePrice)

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_large)))

                AddressSection(event.location)
            }
        }
    }

}

@Composable
private fun TitleSection(
    event: Event,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier) {
        Row {
            Text(
                text = event.title,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge
            )
        }
        Text(
            text = DateFormatHelper.formatDateEventDetail(context, event.date),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
        )
    }
}

@Composable
private fun AddressSection(
    location: Location,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.event_details_section_address_label),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_medium)))

        Card(
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = dimensionResource(id = R.dimen.size_xsmall)
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                Modifier
                    .padding(dimensionResource(id = R.dimen.size_medium))
            ) {
                location.address?.let { address ->
                    Text(text = address, style = MaterialTheme.typography.bodyMedium)
                }
                Text(text = "${location.city}, ${location.country}")
            }
        }
    }
}
