package fr.bendev.seatgeekapp.pages.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.bendev.seatgeekapp.base.BaseViewModelFactory

@Composable
fun EventDetailsScreen(
    id: Long,
    name: String,
    viewModel: EventDetailsViewModel = viewModel(factory = BaseViewModelFactory {
        EventDetailsViewModel(id)
    })
) {
    Column(modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(text = "$id")
        Text(text = name)
    }

}