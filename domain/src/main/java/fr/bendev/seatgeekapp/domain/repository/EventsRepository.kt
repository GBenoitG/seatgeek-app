package fr.bendev.seatgeekapp.domain.repository

import fr.bendev.seatgeekapp.domain.common.ViewResult
import fr.bendev.seatgeekapp.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventsRepository {

    fun fetchEvents(page: Int = 1): Flow<ViewResult<List<Event>>>

}