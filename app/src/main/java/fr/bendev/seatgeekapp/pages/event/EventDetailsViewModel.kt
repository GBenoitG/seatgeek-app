package fr.bendev.seatgeekapp.pages.event

import androidx.lifecycle.viewModelScope
import fr.bendev.seatgeekapp.base.BaseViewModel
import fr.bendev.seatgeekapp.domain.repository.EventsRepository
import fr.bendev.seatgeekapp.framework.di.Injector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class EventDetailsViewModel(
    eventId: Long,
    eventsRepository: EventsRepository = Injector.eventsRepository
) : BaseViewModel() {

    init {
        viewModelScope.launch {
            eventsRepository.getEvent(eventId).collect { viewResult ->
                _isLoading.update { false }
                _modelError.update { null }
                viewResult.handle(
                    onLoading = {
                        _isLoading.update { true }
                    },
                    onSuccess = { events ->
                        Timber.d("Event loaded: ${events?.title}")
                    },
                    onError = {
                        _modelError.update { it }
                    }
                )
            }
        }
    }

}