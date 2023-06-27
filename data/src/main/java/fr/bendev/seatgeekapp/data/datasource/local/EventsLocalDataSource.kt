package fr.bendev.seatgeekapp.data.datasource.local

import fr.bendev.seatgeekapp.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventsLocalDataSource {

    fun savePage(page: Int, events: List<Event>)

    fun loadEvents(): Flow<List<Event>>

}