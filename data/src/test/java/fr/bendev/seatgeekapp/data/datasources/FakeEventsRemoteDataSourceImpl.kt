package fr.bendev.seatgeekapp.data.datasources

import fr.bendev.seatgeekapp.data.datasource.remote.EventsRemoteDataSource
import fr.bendev.seatgeekapp.domain.common.RemoteResult
import fr.bendev.seatgeekapp.domain.common.error.ErrorType
import fr.bendev.seatgeekapp.domain.model.Event

class FakeEventsRemoteDataSourceImpl : EventsRemoteDataSource {

    var fakeApiEventsList: List<Event>? = null

    override suspend fun getEvents(page: Int): RemoteResult<List<Event>> {
        val result = fakeApiEventsList?.let {
            RemoteResult.Success(it)
        } ?: RemoteResult.Error(ErrorType.NOT_FOUND)
        return result
    }

    override suspend fun getEvent(id: Long): RemoteResult<Event> {
        val result = fakeApiEventsList?.find { it.id == id }?.let {
            RemoteResult.Success(it)
        } ?: RemoteResult.Error(ErrorType.NOT_FOUND)
        return result
    }
}