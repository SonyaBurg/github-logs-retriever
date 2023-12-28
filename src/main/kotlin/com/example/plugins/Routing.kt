package com.example.plugins

import com.example.http.RepoNotFoundException
import com.example.repositories.GitRepository
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val gitRepository = GitRepository()
    install(ContentNegotiation) {
        json()
    }

    routing {
        get {
            call.respond(FreeMarkerContent("index.ftl", null))
        }

        post {
            val post = call.receiveParameters()
            val user = post["user"] ?: return@post
            val repo = post["repo"] ?: return@post
            val num = post["num"] ?: return@post

            try {
                val n = if (num == "") 0 else num.toInt()
                val events = gitRepository.getGitEvents(user, repo, n)
                call.respond(FreeMarkerContent("results.ftl", mapOf("events" to events, "name" to repo)))
            } catch (e: RepoNotFoundException) {
                call.respond(
                    FreeMarkerContent(
                        "error.ftl",
                        mapOf("error" to e.message)
                    )
                )
            } catch (e: Exception) {
                println(e.message)
                call.respond(FreeMarkerContent("error.ftl", mapOf("error" to "Internal error occurred!")))
            }
        }
    }
}
