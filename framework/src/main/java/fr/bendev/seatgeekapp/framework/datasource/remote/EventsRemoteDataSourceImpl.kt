package fr.bendev.seatgeekapp.framework.datasource.remote

import fr.bendev.seatgeekapp.data.datasource.remote.EventsRemoteDataSource
import fr.bendev.seatgeekapp.domain.common.RemoteResult
import fr.bendev.seatgeekapp.domain.model.Event
import fr.bendev.seatgeekapp.framework.mappers.fromAPItoDomain
import fr.bendev.seatgeekapp.framework.network.services.EventsService

class EventsRemoteDataSourceImpl(
    private val eventsService: EventsService
) : BaseRemoteDataSource(), EventsRemoteDataSource {

    override suspend fun getEvents(page: Int): RemoteResult<Pair<Int, List<Event>>> =
        getResult({ Pair(page, it?.events?.fromAPItoDomain() ?: emptyList()) }) {
            eventsService.getEventsPage(page)
        }

    override suspend fun getEvent(id: Long): RemoteResult<Event> =
        getResult({ it?.fromAPItoDomain() }) {
            eventsService.getEventById(id)
        }
}