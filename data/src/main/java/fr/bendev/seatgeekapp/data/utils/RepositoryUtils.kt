package fr.bendev.seatgeekapp.data.utils

import fr.bendev.seatgeekapp.domain.common.RemoteResult
import fr.bendev.seatgeekapp.domain.common.ViewResult
import fr.bendev.seatgeekapp.domain.common.error.ErrorType
import fr.bendev.seatgeekapp.domain.common.error.PerformOperationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import timber.log.Timber

fun <T, A> performGetOperation(
    loadData: () -> Flow<T>,
    shouldShowLoading: (T) -> Boolean,
    networkCall: suspend () -> RemoteResult<A>,
    saveApiData: suspend (A?) -> Unit
): Flow<ViewResult<T>> = merge(
    loadData().map {
        if (shouldShowLoading(it)) {
            ViewResult.Loading
        } else {
            Timber.d("Get data from Database")
            ViewResult.Success(it)
        }
    },
    flow {
        Timber.d("Get data from Network")
        when (val result = networkCall()) {
            is RemoteResult.Success -> {
                Timber.d("Save data in Database")
                saveApiData(result.data)
            }

            is RemoteResult.Error -> {
                emit(ViewResult.Error(result.error))
                throw PerformOperationException(result.error)
            }
        }
    }
)

fun <T> performFetchOperation(
    networkCall: suspend () -> RemoteResult<T>,
    saveApiData: suspend (T?) -> Unit
): Flow<ViewResult<T>> = flow {
    Timber.d("Get data from Network")
    when (val result = networkCall()) {
        is RemoteResult.Success -> {
            saveApiData(result.data)
            Timber.d("Save data in Database")
            emit(ViewResult.Success(result.data))
        }

        is RemoteResult.Error -> {
            emit(ViewResult.Error(ErrorType.FETCH_DATA))
            throw PerformOperationException(ErrorType.FETCH_DATA)
        }
    }
}