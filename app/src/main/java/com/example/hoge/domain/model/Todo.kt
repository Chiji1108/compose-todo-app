package com.example.hoge.domain.model

import com.example.hoge.data.database.TodoEntity

data class Todo(
    val id: Long = 0,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

// Extension functions for mapping
fun Todo.toEntity(): TodoEntity {
    return TodoEntity(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}

fun TodoEntity.toDomain(): Todo {
    return Todo(
        id = id,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
} 