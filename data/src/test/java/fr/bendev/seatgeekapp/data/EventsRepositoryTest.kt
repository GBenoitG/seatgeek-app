package fr.bendev.seatgeekapp.data

import fr.bendev.seatgeekapp.data.datasources.FakeEvents
import fr.bendev.seatgeekapp.data.datasources.FakeEventsLocalDataSourceImpl
import fr.bendev.seatgeekapp.data.datasources.FakeEventsRemoteDataSourceImpl
import fr.bendev.seatgeekapp.data.repository.EventsRepositoryImpl
import fr.bendev.seatgeekapp.domain.common.ViewResult
import fr.bendev.seatgeekapp.domain.common.error.ErrorType
import fr.bendev.seatgeekapp.domain.repository.EventsRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EventsRepositoryTest {

    lateinit var repository: EventsRepository

    lateinit var fakeRemoteDataSource: FakeEventsRemoteDataSourceImpl
    lateinit var fakeLocalDataSource: FakeEventsLocalDataSourceImpl

    @Before
    fun setup() {
        val testDispatcher = UnconfinedTestDispatcher()
        fakeRemoteDataSource = FakeEventsRemoteDataSourceImpl()
        fakeLocalDataSource = FakeEventsLocalDataSourceImpl()
        repository = EventsRepositoryImpl(
            fakeRemoteDataSource,
            fakeLocalDataSource,
            testDispatcher
        )
    }


    @Test
    fun `GIVEN a list of 3 events and no save data, WHEN get all events, THEN loading and success`() =
        runTest(UnconfinedTestDispatcher()) {
            // Prepare
            fakeRemoteDataSource.fakeApiEventsList = FakeEvents.get3Events()

            // Test
            val eventsFlow = repository.getEvents()

            // assert
            assertEquals(ViewResult.Loading, eventsFlow.first())
            assertEquals(ViewResult.Success(FakeEvents.get3Events()), eventsFlow.first())
        }

    @Test
    fun `GIVEN a null list no saved data, WHEN get all events, THEN loading and error`() =
        runTest(UnconfinedTestDispatcher()) {
            // Prepare
            fakeRemoteDataSource.fakeApiEventsList = null

            // Test
            val eventsFlow = repository.getEvents()

            // assert
            assertEquals(ViewResult.Loading, eventsFlow.first())
            assertEquals(ViewResult.Error(ErrorType.NOT_FOUND), eventsFlow.drop(1).first())
        }

    @Test
    fun `GIVEN a null list with saved data, WHEN get all events, THEN success`() =
        runTest(UnconfinedTestDispatcher()) {
            // Prepare
            fakeLocalDataSource.eventsPage.value = FakeEvents.get3Events()

            // Test
            val eventsFlow = repository.getEvents()

            // assert
            assertEquals(ViewResult.Success(FakeEvents.get3Events()), eventsFlow.first())
        }

    @Test
    fun `GIVEN a list of 3 events and no saved data, WHEN get an event id 1, THEN loading and success`() =
        runTest(UnconfinedTestDispatcher()) {
            // Prepare
            fakeRemoteDataSource.fakeApiEventsList = FakeEvents.get3Events()

            // Test
            val eventsFlow = repository.getEvent(1)

            // assert
            assertEquals(ViewResult.Loading, eventsFlow.first())
            assertEquals(ViewResult.Success(FakeEvents.event1), eventsFlow.first())
        }

    @Test
    fun `GIVEN a list of 3 events and no saved data, WHEN get an event with bad id, THEN loading and error`() =
        runTest(UnconfinedTestDispatcher()) {
            // Prepare
            fakeRemoteDataSource.fakeApiEventsList = FakeEvents.get3Events()

            // Test
            val eventsFlow = repository.getEvent(-1)

            // assert
            assertEquals(ViewResult.Loading, eventsFlow.first())
            assertEquals(ViewResult.Error(ErrorType.NOT_FOUND), eventsFlow.drop(1).first())
        }

    @Test
    fun `GIVEN a null list with saved data, WHEN get an event with bad id, THEN success`() =
        runTest(UnconfinedTestDispatcher()) {
            // Prepare
            fakeRemoteDataSource.fakeApiEventsList = null
            fakeLocalDataSource.eventsPage.value = FakeEvents.get3Events()

            // Test
            val eventsFlow = repository.getEvent(1)

            // assert
            assertEquals(ViewResult.Success(FakeEvents.event1), eventsFlow.first())
        }
}