package com.example.repositories

import com.example.http.RequestsHandler
import com.example.models.GitEventModel
import org.jetbrains.kotlin.com.google.common.reflect.TypeToken
import org.jetbrains.kotlin.com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class GitRepository {
    private val handler = RequestsHandler()
    fun getGitEvents(user: String, repo: String, num: Int): List<GitEventModel> {
        val request = handler.constructRequest(user, repo)
        val body = handler.sendRequest(request)
        println(body)
        val events = parseBody(body)
        val n = if (num <= 0 || num > events.size) events.size else num
        val result = events.sortedByDescending { it.created_at }.filter { it.type == "PushEvent" }.take(n).map {
            it.created_at_str = convertLongToTime(it.created_at)
            it
        }
        return result
    }

    private fun convertLongToTime(date: Date): String {
        val format = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return format.format(date)
    }

    private fun parseBody(body: String): List<GitEventModel> {
        val typeToken = object : TypeToken<List<GitEventModel>>() {}.type
        return Gson().fromJson(body, typeToken)
    }

}