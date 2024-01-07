package com.gucodero.domain.common.util

import com.gucodero.domain.common.entity.DataResult
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun <T> DataResult<T>.isSuccessful(): Boolean {
    contract {
        returns(true) implies (this@isSuccessful is DataResult.Success)
        returns(false) implies (this@isSuccessful is DataResult.Error)
    }
    return this is DataResult.Success
}

@OptIn(ExperimentalContracts::class)
fun <T> DataResult<T>.isError(): Boolean {
    contract {
        returns(false) implies (this@isError is DataResult.Success)
        returns(true) implies (this@isError is DataResult.Error)
    }
    return this !is DataResult.Success
}

fun <I, O> DataResult<I>.transform(transformer: (I)->O): DataResult<O> {
    return when(this) {
        is DataResult.Success -> DataResult.Success(data = transformer(data))
        is DataResult.Error -> this
    }
}

fun <I, O> DataResult<List<I>>.map(mapper: (I)->O): DataResult<List<O>> {
    return when(this) {
        is DataResult.Success -> DataResult.Success(data = data.map(mapper))
        is DataResult.Error -> this
    }
}

fun <I> DataResult<I>.toUnit(): DataResult<Unit> {
    return this.transform { }
}

inline fun <T> DataResult<T>.ifSuccessful(block: (T) -> Unit): DataResult<T> {
    if (isSuccessful()){
        block(data)
    }
    return this
}

inline fun <T> DataResult<T>.ifError(block: (DataResult.Error) -> Unit): DataResult<T> {
    if (isError()){
        block(this)
    }
    return this
}