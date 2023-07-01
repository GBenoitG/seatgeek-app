package fr.bendev.seatgeekapp.framework.mappers

import fr.bendev.seatgeekapp.domain.model.Event
import fr.bendev.seatgeekapp.domain.model.Location
import fr.bendev.seatgeekapp.domain.model.Stats
import fr.bendev.seatgeekapp.framework.network.model.ApiEvent
import fr.bendev.seatgeekapp.framework.network.model.ApiStats
import fr.bendev.seatgeekapp.framework.network.model.ApiVenue

/**
 * API -> DOMAIN
 */
fun ApiEvent.fromAPItoDomain(): Event = Event(
    id = id,
    title = title,
    date = date,
    endDate = endDate,
    location = venue.fromAPItoDomain(),
    stats = stats.fromAPItoDomain(),
    imageUrl = performers.firstOrNull()?.image
)

fun List<ApiEvent>.fromAPItoDomain(): List<Event> = this.map { it.fromAPItoDomain() }

fun ApiVenue.fromAPItoDomain(): Location = Location(
    address = address,
    country = country,
    city = city
)

fun ApiStats.fromAPItoDomain(): Stats = Stats(
    averagePrice = averagePrice,
    lowestPrice = lowestPrice,
    highestPrice = highestPrice
)