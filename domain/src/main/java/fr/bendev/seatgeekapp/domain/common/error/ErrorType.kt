package fr.bendev.seatgeekapp.domain.common.error

import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Generic error enum class that can be added to to encapsulate all the different errors found in the app
 * All the most common server code error are included in this class
 * -1 is reserved for unknown error (a catch-all code)
 */
enum class ErrorType(vararg val code: Int) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    SERVER_ERROR(*(500..599).distinct().toIntArray()), // Allow fromCode to search in a range
    UNKNOWN_ERROR(-1),
    NO_CONNECTION(-2),
    FETCH_DATA(-3);

    companion object {
        fun fromCode(code: Int): ErrorType =
            values().find { it.code.contains(code) } ?: UNKNOWN_ERROR

        fun fromException(e: Exception): ErrorType = when (e) {
            is UnknownHostException,
            is ConnectException -> NO_CONNECTION

            else -> UNKNOWN_ERROR
        }
    }

    override fun toString(): String {
        return "ErrorType($name=(code: ${code.joinToString(", ")}))"
    }
}