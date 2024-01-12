package com.gucodero.data.people

import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.GET

interface PeopleApi {

    @GET("people/1/")
    suspend fun getPerson(): Response<String>

}