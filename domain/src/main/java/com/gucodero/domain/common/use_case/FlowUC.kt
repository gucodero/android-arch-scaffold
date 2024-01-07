package com.gucodero.domain.common.use_case

import kotlinx.coroutines.flow.Flow

interface FlowUC {
    interface ParamsAndResult<Parameters, Result>: FlowUC {
        suspend operator fun invoke(params: Parameters): Flow<Result>
    }

    interface OptionalParamsAndResult<Parameters, Result>: FlowUC {
        suspend operator fun invoke(params: Parameters? = null): Flow<Result>
    }

    interface OnlyResult<Result>: FlowUC {
        suspend operator fun invoke(): Flow<Result>
    }
}