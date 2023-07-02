package fr.bendev.seatgeekapp.data.datasources

import fr.bendev.seatgeekapp.data.datasource.local.EventsLocalDataSource
import fr.bendev.seatgeekapp.domain.model.Event
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeEventsLocalDataSourceImpl : EventsLocalDataSource {

    val eventsPage = MutableStateFlow<Map<Int, List<Event>>>(hashMapOf())

    override fun savePage(page: Int, events: List<Event>) {
        eventsPage.update {
            val currentMap = it.toMutableMap()
            currentMap[page] = events
            currentMap
        }
    }

    override fun loadEvents(): Flow<List<Event>> = eventsPage.map {
        it.entries.flatMap { entry -> entry.value }
    }

    override fun loadEvent(id: Long): Flow<Event?> = loadEvents().map {
        it.find { event -> event.id == id }
    }
}