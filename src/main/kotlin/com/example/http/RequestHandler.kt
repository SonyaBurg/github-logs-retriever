package com.example.http

import org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.org.apache.http.HttpException
import org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.org.apache.http.HttpStatus
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class RequestsHandler {
    private val client: HttpClient = HttpClient.newBuilder().build()

    fun constructRequest(user: String, repo: String): HttpRequest = HttpRequest.newBuilder()
        .uri(
            URI.create(
                "$BASE_URL$user/$repo/events"

            )
        )
        .header("Authorization", "Bearer $KEY")
        .header("Accept", "application/vnd.github+json")
        .header("X-GitHub-Api-Version", "2022-11-28")
        .build()

    fun sendRequest(request: HttpRequest): String {
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != HttpStatus.SC_OK) {
            if (response.statusCode() == HttpStatus.SC_NOT_FOUND) {
                throw RepoNotFoundException()
            }
            throw HttpException("ERROR: ${response.statusCode()}")
        }
        return response.body() ?: throw HttpException("Null body!")
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/repos/"
        private const val KEY = "<YOUR-KEY>"
    }
}

class RepoNotFoundException : HttpException("Repo not found! Make sure you have the correct user and repo name!")