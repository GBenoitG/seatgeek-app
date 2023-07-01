package fr.bendev.seatgeekapp.pages.event

import androidx.lifecycle.viewModelScope
import fr.bendev.seatgeekapp.base.BaseViewModel
import fr.bendev.seatgeekapp.domain.repository.EventsRepository
import fr.bendev.seatgeekapp.framework.di.Injector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class EventDetailsViewModel(
    eventId: Long,
    eventsRepository: EventsRepository = Injector.eventsRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(EventDetailUIState())
    val uiState: StateFlow<EventDetailUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            eventsRepository.getEvent(eventId).collect { viewResult ->
                _isLoading.update { false }
                _modelError.update { null }
                viewResult.handle(
                    onLoading = {
                        _isLoading.update { true }
                    },
                    onSuccess = { event ->
                        Timber.d("Event loaded: ${event?.title}")
                        _uiState.update { it.copy(event = event) }
                    },
                    onError = {
                        _modelError.update { it }
                    }
                )
            }
        }
    }

}