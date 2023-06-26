package fr.bendev.seatgeekapp.pages.events

import androidx.lifecycle.viewModelScope
import fr.bendev.seatgeekapp.base.BaseViewModel
import fr.bendev.seatgeekapp.domain.repository.EventsRepository
import fr.bendev.seatgeekapp.framework.di.Injector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventsViewModel(
    eventsRepository: EventsRepository = Injector.eventsRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(EventsUIState())
    val uiState: StateFlow<EventsUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            eventsRepository.fetchEvents().collect { viewResult ->
                _isLoading.update { false }
                _modelError.update { null }
                viewResult.handle(
                    onLoading = {
                        _isLoading.update { true }
                    },
                    onSuccess = { events ->
                        _uiState.update { it.copy(events = events ?: emptyList()) }
                    },
                    onError = {
                        _modelError.update { it }
                    }
                )
            }
        }
    }

}