package fr.bendev.seatgeekapp.domain

import fr.bendev.seatgeekapp.domain.common.ViewResult
import fr.bendev.seatgeekapp.domain.common.error.ErrorType
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ViewResultTest {

    @Test
    fun `GIVEN loading state, WHEN toString, THEN loading in string`() {
        // GIVEN
        val viewResult = ViewResult.Loading

        // WHEN
        val test = viewResult.toString()

        // THEN
        assertEquals("Loading", test)
    }

    @Test
    fun `GIVEN success state, WHEN toString, THEN success value in string`() {
        // GIVEN
        val viewResult = ViewResult.Success("success")

        // WHEN
        val test = viewResult.toString()

        // THEN
        assertEquals("Success(data=success)", test)
    }

    @Test
    fun `GIVEN error state, WHEN toString, THEN error value in string`() {
        // GIVEN
        val viewResult = ViewResult.Error(ErrorType.UNKNOWN_ERROR)

        // WHEN
        val test = viewResult.toString()

        // THEN
        assertEquals("Error(error=ErrorType(UNKNOWN_ERROR=(code: -1)))", test)
    }

    @Test
    fun `GIVEN loading state, WHEN handle and onLoading, THEN reached`() =
        runTest(UnconfinedTestDispatcher()) {
            // GIVEN
            val viewResult = ViewResult.Loading
            var isOnLoadingReached = false
            var isAnyCallbackReached = false

            // WHEN
            viewResult.handle(
                onLoading = { isOnLoadingReached = true },
                onSuccess = { isAnyCallbackReached = true },
                onError = { isAnyCallbackReached = true }
            )

            // THEN
            assertTrue(isOnLoadingReached)
            assertFalse(isAnyCallbackReached)
        }

    @Test
    fun `GIVEN loading state, WHEN handle and no onLoading, THEN not reached`() =
        runTest(UnconfinedTestDispatcher()) {
            // GIVEN
            val viewResult = ViewResult.Loading
            val isOnLoadingReached = false
            var isAnyCallbackReached = false

            // WHEN
            viewResult.handle(
                onSuccess = { isAnyCallbackReached = true },
                onError = { isAnyCallbackReached = true }
            )

            // THEN
            assertFalse(isOnLoadingReached)
            assertFalse(isAnyCallbackReached)
        }

    @Test
    fun `GIVEN success state, WHEN handle and onSuccess, THEN reached`() =
        runTest(UnconfinedTestDispatcher()) {
            // GIVEN
            val viewResult = ViewResult.Success("success")
            var onSuccessValueReached = "NotReached"
            var isAnyCallbackReached = false

            // WHEN
            viewResult.handle(
                onLoading = { isAnyCallbackReached = true },
                onSuccess = { onSuccessValueReached = it!! },
                onError = { isAnyCallbackReached = true }
            )

            // THEN
            assertEquals("success", onSuccessValueReached)
            assertFalse(isAnyCallbackReached)
        }

    @Test
    fun `GIVEN success state, WHEN handle and no onSuccess, THEN not reached`() =
        runTest(UnconfinedTestDispatcher()) {
            // GIVEN
            val viewResult = ViewResult.Success("success")
            val onSuccessValueReached = "NotReached"
            var isAnyCallbackReached = false

            // WHEN
            viewResult.handle(
                onLoading = { isAnyCallbackReached = true },
                onError = { isAnyCallbackReached = true }
            )

            // THEN
            assertEquals("NotReached", onSuccessValueReached)
            assertFalse(isAnyCallbackReached)
        }

    @Test
    fun `GIVEN error state, WHEN handle and onError, THEN reached`() =
        runTest(UnconfinedTestDispatcher()) {
            // GIVEN
            val viewResult = ViewResult.Error(ErrorType.UNKNOWN_ERROR)
            var onErrorValueReached: ErrorType? = null
            var isAnyCallbackReached = false

            // WHEN
            viewResult.handle(
                onLoading = { isAnyCallbackReached = true },
                onSuccess = { isAnyCallbackReached = true },
                onError = { onErrorValueReached = it }
            )

            // THEN
            assertEquals(ErrorType.UNKNOWN_ERROR, onErrorValueReached)
            assertFalse(isAnyCallbackReached)
        }

    @Test
    fun `GIVEN error state, WHEN handle and no onError, THEN not reached`() =
        runTest(UnconfinedTestDispatcher()) {
            // GIVEN
            val viewResult = ViewResult.Error(ErrorType.UNKNOWN_ERROR)
            val onErrorValueReached: ErrorType? = null
            var isAnyCallbackReached = false

            // WHEN
            viewResult.handle(
                onLoading = { isAnyCallbackReached = true },
                onSuccess = { isAnyCallbackReached = true },
            )

            // THEN
            assertNull(onErrorValueReached)
            assertFalse(isAnyCallbackReached)
        }
}