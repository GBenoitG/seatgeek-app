package fr.bendev.seatgeekapp.domain.common.error

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : NetworkResult<T>()
    data class Failure(val error: ErrorType, val message: String? = null) : NetworkResult<Nothing>()
}