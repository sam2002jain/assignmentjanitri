package com.example.jainitri
//sanyam jain
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = ColorDatabase.getInstance(this)
        val repository = ColorRepository(database.colorDao())
        val viewModelFactory = ColorViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ColorViewModel::class.java]
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    ColorApp(viewModel)
                }
            }
        }
    }
}
