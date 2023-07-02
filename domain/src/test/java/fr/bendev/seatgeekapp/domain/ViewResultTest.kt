package fr.bendev.seatgeekapp.domain

import fr.bendev.seatgeekapp.domain.common.ViewResult
import fr.bendev.seatgeekapp.domain.common.error.ErrorType
import junit.framework.TestCase.assertEquals
import org.junit.Test

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

}