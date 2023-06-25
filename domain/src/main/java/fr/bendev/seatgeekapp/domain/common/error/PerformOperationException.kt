package fr.bendev.seatgeekapp.domain.common.error

data class PerformOperationException(val error: ErrorType) : Exception()
