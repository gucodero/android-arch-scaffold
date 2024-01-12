package com.gucodero.data.common.util

import com.gucodero.domain.common.entity.DataResult
import de.jensklingenberg.ktorfit.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T : Any> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Response<T>
): DataResult<T> {
    return withContext(dispatcher) {
        try {
            val result = apiCall()
            if (result.isSuccessful) {
                DataResult.Success(result.body()!!)
            } else {
                DataResult.Error(
                    code = result.code.toString(),
                    message = result.message,
                    body = result.errorBody()?.toString()
                )
            }
        } catch (ex: Exception) {
            DataResult.Error(
                message = ex.message,
                cause = ex
            )
        }
    }
}