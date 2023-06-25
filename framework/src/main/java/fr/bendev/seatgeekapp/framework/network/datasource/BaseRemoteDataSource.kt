package fr.bendev.seatgeekapp.framework.network.datasource

import fr.bendev.seatgeekapp.domain.common.RemoteResult
import fr.bendev.seatgeekapp.domain.common.error.ErrorType
import retrofit2.Response
import timber.log.Timber

abstract class BaseRemoteDataSource {

    protected suspend fun <T : Any> getResult(networkCall: suspend () -> Response<T>): RemoteResult<T> {
        try {
            val response = networkCall()
            if (response.isSuccessful) {
                val body = response.body()
                return RemoteResult.Success(body)
            }
            return error(ErrorType.fromCode(response.code()))
        } catch (e: Exception) {
            Timber.e(e)
            return error(ErrorType.fromException(e))
        }
    }

    /**
     * getResult is a suspended function to get result from a retrofit api call returning a
     * Response<T>.
     *
     * Params:
     *      @param converter is a lambda that execute a given mapping function
     *      @param networkCall is a suspended lambda that allows to execute suspended function
     *
     * @return RemoteResult<R>
     */
    protected suspend fun <T : Any, R : Any> getResult(
        converter: (T?) -> R?,
        networkCall: suspend () -> Response<T>
    ): RemoteResult<R> {
        try {
            val response = networkCall()
            if (response.isSuccessful) {
                val body = response.body()
                return RemoteResult.Success(converter(body))
            }
            return error(ErrorType.fromCode(response.code()))
        } catch (e: Exception) {
            Timber.e(e)
            return error(ErrorType.fromException(e))
        }
    }

    private fun error(errorType: ErrorType): RemoteResult.Error {
        Timber.w(errorType.toString())
        return RemoteResult.Error(errorType)
    }

}