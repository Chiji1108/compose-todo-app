package com.example.hoge.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hoge.ui.screens.AddTodoScreen
import com.example.hoge.ui.screens.TodoListScreen
import com.example.hoge.ui.viewmodel.TodoViewModel

object TodoDestinations {
    const val TODO_LIST = "todo_list"
    const val ADD_TODO = "add_todo"
}

@Composable
fun TodoNavigation(
    navController: NavHostController,
    viewModel: TodoViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TodoDestinations.TODO_LIST,
        modifier = modifier
    ) {
        composable(TodoDestinations.TODO_LIST) {
            TodoListScreen(
                viewModel = viewModel,
                onNavigateToAddTodo = {
                    navController.navigate(TodoDestinations.ADD_TODO)
                }
            )
        }

        composable(TodoDestinations.ADD_TODO) {
            AddTodoScreen(
                viewModel = viewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
} 