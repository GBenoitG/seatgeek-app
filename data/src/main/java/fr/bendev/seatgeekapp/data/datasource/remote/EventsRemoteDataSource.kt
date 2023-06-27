package fr.bendev.seatgeekapp.data.datasource.remote

import fr.bendev.seatgeekapp.domain.common.RemoteResult
import fr.bendev.seatgeekapp.domain.model.Event

interface EventsRemoteDataSource {

    suspend fun getEvents(page: Int): RemoteResult<Pair<Int, List<Event>>>

}