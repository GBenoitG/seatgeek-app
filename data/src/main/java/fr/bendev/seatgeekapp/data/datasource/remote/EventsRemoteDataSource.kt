package fr.bendev.seatgeekapp.data.datasource.remote

import fr.bendev.seatgeekapp.domain.common.RemoteResult
import fr.bendev.seatgeekapp.domain.model.Event

interface EventsRemoteDataSource {

    /**
     * Get from API a list of events at a certain page, return a Pair of page index and its content
     */
    suspend fun getEvents(page: Int): RemoteResult<Pair<Int, List<Event>>>

}