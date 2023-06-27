package fr.bendev.seatgeekapp.pages.events

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.bendev.seatgeekapp.R
import fr.bendev.seatgeekapp.domain.model.Event
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    title: String,
    onEventClick: (id: Long, name: String) -> Unit,
    viewModel: EventsViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title, style = MaterialTheme.typography.titleLarge) }
            )
        }
    ) {
        Content(
            uiState = uiState,
            onNextPage = {
                viewModel.setNextPage()
            },
            onEventClick = onEventClick,
            isLoading = isLoading,
            modifier = Modifier
                .padding(it)
        )
    }
}

@Composable
private fun Content(
    uiState: EventsUIState,
    onNextPage: () -> Unit,
    onEventClick: (id: Long, name: String) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    val scrollState = rememberLazyListState()

    val isAtBottom by remember {
        derivedStateOf {
            val layoutInfo = scrollState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (layoutInfo.totalItemsCount <= 2) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()
                val viewportHeight =
                    layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset

                (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount &&
                        lastVisibleItem.offset + lastVisibleItem.size <= viewportHeight)
            }
        }
    }

    if (isAtBottom) {
        onNextPage()
    }

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        EventsList(
            uiState.events,
            scrollState,
            onEventClick,
            Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.size_medium))
        )
        if (isLoading) {
            Timber.d("ShowLoading")
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun EventsList(
    events: List<Event>,
    scrollState: LazyListState,
    onEventClick: (id: Long, name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        scrollState
    ) {
        item {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_small)))
        }

        itemsIndexed(events) { index, item ->
            EventRow(
                name = item.title,
                date = item.date,
                price = item.stats.averagePrice,
                country = item.location.country,
                city = item.location.city,
                onClick = {
                    onEventClick(item.id, item.title)
                },
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            )
            if (index < events.lastIndex) {
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_medium)))
            }
        }

        item {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.size_medium)))
        }
    }
}