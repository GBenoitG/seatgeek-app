package fr.bendev.seatgeekapp.domain.model

import java.util.Date

data class Event(
    val id: Long,
    val title: String,
    val date: Date,
    val endDate: Date?,
    val location: Location,
    val stats: Stats

)
