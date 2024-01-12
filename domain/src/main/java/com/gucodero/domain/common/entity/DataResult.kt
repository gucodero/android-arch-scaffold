package com.gucodero.domain.common.entity

sealed class DataResult<out T> {
    data class Success<out T>(
        val data: T
    ): DataResult<T>()
    open class Error(
        val code: String? = null,
        val message: String? = null,
        val body: String? = null,
        val cause: Throwable? = null
    ): DataResult<Nothing>()
}