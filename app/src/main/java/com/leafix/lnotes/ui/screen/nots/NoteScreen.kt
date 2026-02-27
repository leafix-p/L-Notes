package com.leafix.lnotes.ui.screen.nots

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
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
        Scaffold(
            floatingActionButton = {
                AddButton()
            }
        ) { innerPadding ->
            // 笔记列表
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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

}

///**
// * 加号按钮
// */
//@Preview
//@Composable
//fun AddButton() {
//    IconButton(
//        onClick = {
//            // TODO: 打开添加笔记界面
//        },
//        modifier = Modifier
//            .clip(CircleShape)
//            .size(48.dp)
//            .background(Color.DarkGray),
//        content = {
//            Icon(
//                imageVector = Icons.Default.Create,
//                contentDescription = "Add",
//                tint = Color.White,
//            )
//        }
//    )
//}

///**
// * 加号按钮
// */
//@Preview
//@Composable
//fun AddButton(onClick: () -> Unit = {}) {
//    FilledIconButton(
//        onClick = onClick,
////        shape = RoundedCornerShape(12.dp),   // 圆角矩形
//        shape = CircleShape,    // 圆形
//        colors = IconButtonDefaults.filledIconButtonColors(
//            containerColor = Color.Blue,
//            contentColor = Color.White
//        ),
//        modifier = Modifier.size(48.dp)
//    ) {
//        Icon(Icons.Default.Create, contentDescription = "Add")
//    }
//}

/**
 * 悬浮按钮
 */
@Preview
@Composable
fun AddButton(onClick: () -> Unit = {}) {
    FloatingActionButton(
        onClick = onClick,
//        shape = RoundedCornerShape(12.dp),   // 圆角矩形
        shape = CircleShape,    // 圆形
        containerColor = Color.LightGray,  // 背景色
        contentColor = Color.White, // 图标颜色
        modifier = Modifier.size(48.dp)
    ) {
        Icon(Icons.Default.Create, contentDescription = "Add")
    }
}