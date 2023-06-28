package fr.bendev.seatgeekapp.data.datasource.local

import fr.bendev.seatgeekapp.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventsLocalDataSource {

    /**
     * Save a page index and its content into local/cache storage
     */
    fun savePage(page: Int, events: List<Event>)

    /**
     * Return a flow of events list get from local/cache storage
     */
    fun loadEvents(): Flow<List<Event>>

    /**
     * Return a flow of event detail for a given id
     */
    fun loadEvent(id: Long): Flow<Event?>

}