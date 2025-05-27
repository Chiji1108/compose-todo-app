package com.example.hoge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.hoge.data.database.TodoDatabase
import com.example.hoge.data.repository.TodoRepository
import com.example.hoge.navigation.TodoNavigation
import com.example.hoge.ui.theme.HogeTheme
import com.example.hoge.ui.viewmodel.TodoViewModel
import com.example.hoge.ui.viewmodel.TodoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HogeTheme {
                val database = TodoDatabase.getDatabase(this)
                val repository = TodoRepository(database.todoDao())
                val viewModelFactory = TodoViewModelFactory(repository)
                val viewModel: TodoViewModel = viewModel(factory = viewModelFactory)
                val navController = rememberNavController()

                TodoNavigation(
                    navController = navController,
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}