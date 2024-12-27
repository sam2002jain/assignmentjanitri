package com.example.jainitri

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ColorViewModel(private val repository: ColorRepository) : ViewModel() {

    private val _colors = MutableStateFlow<List<ColorEntity>>(emptyList())
    val colors: StateFlow<List<ColorEntity>> get() = _colors

    init {
        loadColors()
    }

    fun addColor(color: String, timestamp: Long) {
        val newColor = ColorEntity(color = color, timestamp = timestamp)
        viewModelScope.launch {
            repository.insertColor(newColor)
            loadColors()
        }
    }

    private fun loadColors() {
        viewModelScope.launch {
            _colors.value = repository.getAllColors()
        }
    }
}

class ColorViewModelFactory(private val repository: ColorRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ColorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ColorViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
