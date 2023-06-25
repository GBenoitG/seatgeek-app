package fr.bendev.seatgeekapp.domain.common

import fr.bendev.seatgeekapp.domain.common.error.ErrorType

sealed class RemoteResult<out T> {

    data class Success<out T : Any>(val data: T?) : RemoteResult<T>()
    data class Error(val error: ErrorType) : RemoteResult<Nothing>()

    override fun toString(): String = when (this) {
        is Success<*> -> "Success[data=$data]"
        is Error -> "Error[error=$error]"
    }

    fun toViewResult(): ViewResult<T> = when (this) {
        is Success -> ViewResult.Success(this.data)
        is Error -> ViewResult.Error(this.error)
    }

}