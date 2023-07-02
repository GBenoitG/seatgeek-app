package fr.bendev.seatgeekapp.data.datasources

import fr.bendev.seatgeekapp.domain.model.Event
import fr.bendev.seatgeekapp.domain.model.Location
import fr.bendev.seatgeekapp.domain.model.Stats
import java.util.Date

private const val DATE_TIMESTAMP = 1683151200000

object FakeEvents {

    val event1 = Event(
        1L,
        "title1",
        Date(DATE_TIMESTAMP),
        null,
        Location(
            "1 rue de la république",
            "France",
            "Lyon"
        ),
        Stats(
            null,
            null,
            null
        ),
        null
    )

    fun get3Events() = listOf(
        event1,
        Event(
            2L,
            "title2",
            Date(DATE_TIMESTAMP),
            null,
            Location(
                "2 rue de la république",
                "France",
                "Lyon"
            ),
            Stats(
                1f,
                null,
                null
            ),
            null
        ),
        Event(
            3L,
            "title3",
            Date(DATE_TIMESTAMP),
            null,
            Location(
                "3 rue de la république",
                "France",
                "Lyon"
            ),
            Stats(
                12.5f,
                10f,
                20.30f
            ),
            null
        )
    )
}
