package com.leafix.lnotes.data

data class TodoItem(
    val id: Int,
    val task: String,
    var isDone: Boolean = false
)
