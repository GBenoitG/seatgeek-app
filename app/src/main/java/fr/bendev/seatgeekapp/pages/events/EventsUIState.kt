package fr.bendev.seatgeekapp.pages.events

import fr.bendev.seatgeekapp.domain.model.Event

data class EventsUIState(
    val events: List<Event> = emptyList()
)
