package com.gucodero.data.people

import retrofit2.Response
import retrofit2.http.GET

interface PeopleApi {

    @GET("people/1/")
    suspend fun getPerson(): Response<Map<Any, Any>>

}