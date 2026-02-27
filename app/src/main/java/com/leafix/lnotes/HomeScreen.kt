package com.leafix.lnotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.leafix.lnotes.ui.NoteScreen
import com.leafix.lnotes.ui.TodoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun HomeScreen() {

    // 1. 获取当前路由状态
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // 2. 获取当前页面标题,用于顶栏显示
    val currentScreen = items.find { it.route == currentDestination?.route } ?: Screen.Notes
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            // 2. 使用 LargeTopAppBar 实现标题从左下移动到中间顶部的效果
            LargeTopAppBar(
                title = {
                    Text(
                        text = currentScreen.title,
                        fontSize = 24.sp,
                    )
                },
                actions = {
                    IconButton(onClick = { /* 设置按钮 */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                // 将滚动行为传递给LargeTopAppBar
                scrollBehavior = scrollBehavior
            )
        },
        bottomBar = {
            // 3. 底部导航栏
            NavigationBar {
                items.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true

                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                // 避免重复导航
                                popUpTo(
                                    navController.graph.findStartDestination().id
                                ) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // 3. 内容区域,可滚动
        NavHost(
            navController = navController,
            startDestination = Screen.Notes.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Notes.route) { NoteScreen() }
            composable(Screen.Todo.route) { TodoScreen() }
        }
    }
}

@Preview
@Composable
fun NotesPreview() {
    NoteScreen()
}

@Preview
@Composable
fun TodoPreview() {
    TodoScreen()
}