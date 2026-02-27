package com.leafix.lnotes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.leafix.lnotes.data.TodoItem

@Composable
fun TodoScreen() {
    // 1. 状态管理: 使用remember保存列表状态
    var taskText by remember { mutableStateOf("") }
    val taskList = remember {
        mutableStateListOf<TodoItem>(
        )
    }


    // 2. 绘制界面
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(text = "Todo List \uD83D\uDCDD", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // 3. 输入区域
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = taskText,
                onValueChange = { taskText = it },
                modifier = Modifier
                    .weight(1f)
                    .alignByBaseline(), // 让输入框根据其文本基线对齐,
                label = { Text("Add Task") },
            )

            // 为了视觉效果，可以在输入框和按钮之间加一点点间距
            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    // 添加任务的逻辑
                    taskList.add(TodoItem(taskList.size, taskText))
                },
//                modifier = Modifier
//                    .alignByBaseline()
            ) {
                Text("Add")
            }
        }

        // 4. 列表区域
        LazyColumn( // 替代RecyclerView
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(taskList) { item ->
                TodoRow(item)
            }

        }

    }
}


@Composable
fun TodoRow(item: TodoItem) {
    var checked by remember { mutableStateOf(item.isDone) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = {
                    checked = it
                    item.isDone = it
                }
            )
            Text(
                text = item.task,
                style = if (checked) {
                    MaterialTheme.typography.bodyLarge.copy(
                        textDecoration = TextDecoration.LineThrough // 添加删除线
                    )
                } else {
                    MaterialTheme.typography.bodyLarge
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DevPreview() {
    TodoScreen()
}