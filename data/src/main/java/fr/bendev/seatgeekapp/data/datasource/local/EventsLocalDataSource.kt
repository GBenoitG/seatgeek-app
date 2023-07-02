package fr.bendev.seatgeekapp.data.datasource.local

import fr.bendev.seatgeekapp.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventsLocalDataSource {

    /**
     * Save a list of events into local/cache storage
     */
    fun saveEvents(events: List<Event>)

    /**
     * Save an event into local/cache storage
     */
    fun saveEvent(event: Event)

    /**
     * Return a flow of events list get from local/cache storage
     */
    fun loadEvents(): Flow<List<Event>>

    /**
     * Return a flow of event detail for a given id
     */
    fun loadEvent(id: Long): Flow<Event?>

}