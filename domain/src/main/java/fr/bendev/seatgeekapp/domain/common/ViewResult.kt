package fr.bendev.seatgeekapp.domain.common

import fr.bendev.seatgeekapp.domain.common.error.ErrorType

sealed class ViewResult<out T> {

    object Loading : ViewResult<Nothing>()
    data class Success<out T : Any>(val data: T?) : ViewResult<T>()
    data class Error(val error: ErrorType) : ViewResult<Nothing>()

    override fun toString(): String = when (this) {
        is Success<*> -> "Success(data=$data)"
        is Error -> "Error(error=$error)"
        is Loading -> "Loading"
    }

    suspend fun handle(
        onLoading: () -> Unit = {},
        onSuccess: suspend (T?) -> Unit = {},
        onError: (ErrorType) -> Unit = {}
    ) {

        return when (this) {
            is Loading -> onLoading()
            is Success -> onSuccess(data)
            is Error -> onError(error)
        }
    }

}