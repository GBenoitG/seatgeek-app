package fr.bendev.seatgeekapp.data.repository

import fr.bendev.seatgeekapp.data.datasource.remote.EventsRemoteDataSource
import fr.bendev.seatgeekapp.data.utils.performFetchOperation
import fr.bendev.seatgeekapp.domain.common.ViewResult
import fr.bendev.seatgeekapp.domain.model.Event
import fr.bendev.seatgeekapp.domain.repository.EventsRepository
import kotlinx.coroutines.flow.Flow

class EventsRepositoryImpl(
    private val eventsRemoteDataSource: EventsRemoteDataSource
) : EventsRepository {

    override fun fetchEvents(page: Int): Flow<ViewResult<List<Event>>> =
        performFetchOperation(
            networkCall = { eventsRemoteDataSource.getEvents(page) },
            saveApiData = {}
        )
}