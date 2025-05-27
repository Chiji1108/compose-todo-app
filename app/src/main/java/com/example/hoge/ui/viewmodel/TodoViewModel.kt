package com.example.hoge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hoge.data.repository.TodoRepository
import com.example.hoge.domain.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos() {
        viewModelScope.launch {
            repository.getAllTodos().collect { todoList ->
                _todos.value = todoList
            }
        }
    }

    fun addTodo(title: String, description: String = "") {
        if (title.isBlank()) return
        
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newTodo = Todo(
                    title = title.trim(),
                    description = description.trim()
                )
                repository.insertTodo(newTodo)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleTodoCompletion(todo: Todo) {
        viewModelScope.launch {
            repository.toggleTodoCompletion(todo.id, !todo.isCompleted)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            repository.updateTodo(todo)
        }
    }
} 