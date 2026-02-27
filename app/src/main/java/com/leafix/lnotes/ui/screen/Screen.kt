package com.leafix.lnotes.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String, val title: String, val icon: ImageVector
) {
    object Notes : Screen("notes", "笔记", Icons.Default.Edit)
    object Todo : Screen("todo", "待办", Icons.Default.CheckCircle)
}

val items = listOf(
    Screen.Notes,
    Screen.Todo
)