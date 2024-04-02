package com.ncgroup.whichcat.main.cats.data.remote.service

import android.util.Log
import com.ncgroup.whichcat.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.json.JsonPlugin
import io.ktor.client.plugins.kotlinx.serializer.KotlinxSerializer
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json


private val URL = "https://api.thecatapi.com/v1/images/search"

class CatsService {

    private val client = HttpClient(Android) {
        install(JsonPlugin) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            object : Logger {
                override fun log(message: String) {
                    Log.v("request:", message)
                }
            }
            level = LogLevel.BODY
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header("x-api-key", BuildConfig.apiKey)
        }
    }

    suspend fun getCat(
        page: Int,
        limit: Int,
        breedId: String
    ): HttpResponse {

        require(BuildConfig.apiKey.isNotBlank()){
            Throwable("Please add you api token in local.properties")
        }

        return client.get {
            url(URL)
            parameter("page", page)
            parameter("limit", limit)
            parameter("breed_ids", breedId)
        }
    }

}
