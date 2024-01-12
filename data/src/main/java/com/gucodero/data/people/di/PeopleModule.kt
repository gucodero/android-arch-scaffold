package com.gucodero.data.people.di

import com.gucodero.data.people.PeopleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE

@Module
@InstallIn(SingletonComponent::class)
class PeopleModule {

    @Provides
    fun providePeopleApi(): PeopleApi {
        return Ktorfit.Builder()
            .httpClient {
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.ALL
                }
            }
            .baseUrl("https://swapi.dev/api/")
            .build()
            .create()
    }

}