package fr.bendev.seatgeekapp.framework.datasource.local

import fr.bendev.seatgeekapp.data.datasource.local.EventsLocalDataSource
import fr.bendev.seatgeekapp.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class EventsLocalDataSourceImpl : EventsLocalDataSource {

    private val eventsPage = MutableStateFlow<List<Event>>(emptyList())

    override fun saveEvents(events: List<Event>) {
        eventsPage.update {
            val currentList = it.toMutableList()
            currentList.addAll(events)
            currentList.distinctBy { event -> event.id }
        }
    }

    override fun saveEvent(event: Event) {
        eventsPage.update {
            val currentList = it.toMutableList()
            currentList.add(event)
            currentList.distinctBy { event -> event.id }
        }
    }

    override fun loadEvents(): Flow<List<Event>> = eventsPage

    override fun loadEvent(id: Long): Flow<Event?> = loadEvents().map {
        it.find { event -> event.id == id }
    }
}