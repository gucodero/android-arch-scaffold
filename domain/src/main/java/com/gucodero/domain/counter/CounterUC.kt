package com.gucodero.domain.counter

import com.gucodero.domain.common.use_case.SimpleUC
import javax.inject.Inject

class CounterUC @Inject constructor(): SimpleUC.ParamsAndResult<CounterUC.Params, CounterUC.Result> {

    override suspend fun invoke(params: Params): Result {
        return when(params) {
            is Params.Increment -> {
                Result.Success(
                    value = params.value + 1
                )
            }
            is Params.Decrement -> {
                if (params.value == 0) return Result.DecrementNotValid
                Result.Success(
                    value = params.value - 1
                )
            }
        }
    }

    sealed class Params {
        data class Increment(
            val value: Int
        ): Params()
        data class Decrement(
            val value: Int
        ): Params()
    }

    sealed class Result {
        data class Success(
            val value: Int
        ): Result()
        data object DecrementNotValid: Result()
    }

}