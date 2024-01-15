package com.gucodero.data.common.util

import com.google.gson.Gson
import com.gucodero.domain.common.entity.DataResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

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
                    code = result.code().toString(),
                    message = result.errorBody()?.stringSuspending(),
                    body = result.errorBody()?.toString()
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            DataResult.Error(
                message = ex.message,
                cause = ex
            )
        }
    }
}

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun ResponseBody.stringSuspending() = try {
    withContext(Dispatchers.IO) { string() }
} catch (_: Exception){ null }