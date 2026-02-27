package com.leafix.lnotes.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
) {
    var text by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) } // 统一的状态变量

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = 8.dp, top = 0.dp)
    ) {
        SearchBar(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
            windowInsets = WindowInsets(0.dp),
            inputField = {
                // 2. 内部 InputField 也需要这些参数来处理点击行为
                SearchBarDefaults.InputField(
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = { isExpanded = false }, // 搜索后收起
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it }, // 必须传这个参数
                    placeholder = { Text("搜索笔记...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            // 3. 展开后的面板内容
            Column(Modifier.verticalScroll(rememberScrollState())) {
                repeat(4) { index ->
                    ListItem(
                        headlineContent = { Text("搜索建议 $index") },
                        modifier = Modifier.clickable {
                            text = "建议 $index"
                            isExpanded = false
                        }
                    )
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(20) {
                ListItem(
                    headlineContent = { Text("笔记 $it") },

                    modifier = Modifier.clickable {
                        text = "笔记 $it"
                        isExpanded = false
                    }
                )
            }
        }
    }

}