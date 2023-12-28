package com.example.models

import java.util.Date

data class GitEventModel(val type: String, val payload: Payload, val created_at: Date, var created_at_str: String?, val repo: Repo)

data class Payload(val repository_id: Int, val push_id: Long, val size: Int, val before: String?, val head: String?, val commits: List<Commit>?)

data class Commit(val author: Author, val message: String)

data class Author(val email: String, val name: String)

data class Repo(val id: Int, val name: String, val url: String)