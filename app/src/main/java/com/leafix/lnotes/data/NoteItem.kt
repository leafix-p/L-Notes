package com.leafix.lnotes.data

import android.net.Uri

data class NoteItem(
    val id: Int,
    val title: String,
    val content: String,
    val date: String,
    val preImage: Uri? = null
)
