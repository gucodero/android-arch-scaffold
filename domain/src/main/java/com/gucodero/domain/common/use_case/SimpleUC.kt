package com.gucodero.domain.common.use_case

interface SimpleUC {
    interface ParamsAndResult<Parameters, Result>: SimpleUC {
        suspend operator fun invoke(params: Parameters): Result
    }

    interface ParamsAndOptionalResult<Parameters, Result>: SimpleUC {
        suspend operator fun invoke(params: Parameters): Result?
    }

    interface OptionalParamsAndResult<Parameters, Result>: SimpleUC {
        suspend operator fun invoke(params: Parameters? = null): Result
    }

    interface OnlyParams<Parameters>: SimpleUC {
        suspend operator fun invoke(params: Parameters)
    }

    interface OnlyResult<Result>: SimpleUC {
        suspend operator fun invoke(): Result
    }

    interface Empty: SimpleUC {
        suspend operator fun invoke()
    }
}