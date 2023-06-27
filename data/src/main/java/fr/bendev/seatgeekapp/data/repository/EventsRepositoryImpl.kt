package fr.bendev.seatgeekapp.data.repository

import fr.bendev.seatgeekapp.data.datasource.local.EventsLocalDataSource
import fr.bendev.seatgeekapp.data.datasource.remote.EventsRemoteDataSource
import fr.bendev.seatgeekapp.data.utils.performGetOperation
import fr.bendev.seatgeekapp.domain.common.ViewResult
import fr.bendev.seatgeekapp.domain.model.Event
import fr.bendev.seatgeekapp.domain.repository.EventsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class EventsRepositoryImpl(
    private val eventsRemoteDataSource: EventsRemoteDataSource,
    private val eventsLocalDataSource: EventsLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : EventsRepository {

    override fun getEvents(page: Int): Flow<ViewResult<List<Event>>> =
        performGetOperation(
            loadData = { eventsLocalDataSource.loadEvents() },
            isDataEmpty = { it.isEmpty() },
            networkCall = { eventsRemoteDataSource.getEvents(page) },
            saveApiData = { it?.let { eventsLocalDataSource.savePage(it.first, it.second) } }
        ).flowOn(dispatcher)
}