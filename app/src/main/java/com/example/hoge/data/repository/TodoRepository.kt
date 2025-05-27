package com.example.hoge.data.repository

import com.example.hoge.data.database.TodoDao
import com.example.hoge.domain.model.Todo
import com.example.hoge.domain.model.toDomain
import com.example.hoge.domain.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepository(private val todoDao: TodoDao) {
    
    fun getAllTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun getTodoById(id: Long): Todo? {
        return todoDao.getTodoById(id)?.toDomain()
    }

    suspend fun insertTodo(todo: Todo): Long {
        return todoDao.insertTodo(todo.toEntity())
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo.toEntity())
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo.toEntity())
    }

    suspend fun toggleTodoCompletion(id: Long, isCompleted: Boolean) {
        todoDao.updateTodoCompletion(id, isCompleted)
    }
} 