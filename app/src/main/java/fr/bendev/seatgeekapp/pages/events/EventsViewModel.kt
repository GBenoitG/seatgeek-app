package fr.bendev.seatgeekapp.pages.events

import androidx.lifecycle.viewModelScope
import fr.bendev.seatgeekapp.base.BaseViewModel
import fr.bendev.seatgeekapp.domain.repository.EventsRepository
import fr.bendev.seatgeekapp.framework.di.Injector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class EventsViewModel(
    eventsRepository: EventsRepository = Injector.eventsRepository
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(EventsUIState())
    val uiState: StateFlow<EventsUIState> = _uiState.asStateFlow()

    private val _pageInfo = MutableStateFlow(PageInfo())

    private val eventsFlow = _pageInfo.flatMapMerge { pageInfo ->
        eventsRepository.getEvents(pageInfo.currentPage)
    }

    init {
        viewModelScope.launch {
            eventsFlow.collect { viewResult ->
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

    fun setNextPage() {
        _pageInfo.update { it.copy(currentPage = it.nextPage) }
    }

}

private data class PageInfo(
    val currentPage: Int = 1,
) {

    val nextPage: Int
        get() = currentPage + 1

    val previousPage: Int
        get() = Integer.max(currentPage - 1, 1)
}