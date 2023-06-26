package fr.bendev.seatgeekapp.framework.network.datasource

import fr.bendev.seatgeekapp.data.datasource.remote.EventsRemoteDataSource
import fr.bendev.seatgeekapp.domain.common.RemoteResult
import fr.bendev.seatgeekapp.domain.model.Event
import fr.bendev.seatgeekapp.framework.mappers.fromAPItoDomain
import fr.bendev.seatgeekapp.framework.network.services.EventsService

class EventsRemoteDataSourceImpl(
    private val eventsService: EventsService
) : BaseRemoteDataSource(), EventsRemoteDataSource {

    override suspend fun getEvents(page: Int): RemoteResult<List<Event>> =
        getResult({ it?.events?.fromAPItoDomain() }) {
            eventsService.getEventsPage(page)
        }
}