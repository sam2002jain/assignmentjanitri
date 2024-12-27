package com.example.jainitri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ColorApp(viewModel: ColorViewModel) {
    val colors by viewModel.colors.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Color App", fontSize = 20.sp, color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF5659A4)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addColor(generateRandomColor(), System.currentTimeMillis())
                },
                containerColor = Color(0xFFADB6FF)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Color", tint = Color(0xFF5B5ED0))
            }
        }
    ) { padding ->
        ColorGrid(colors.map { ColorItem(it.color, it.timestamp) }, Modifier.padding(padding))
    }
}


@Composable
fun ColorGrid(colors: List<ColorItem>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(colors) { color ->
            ColorCard(color)
        }
    }
}

@Composable
fun ColorCard(colorItem: ColorItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.2f),
        colors = CardDefaults.cardColors(
            containerColor = Color(android.graphics.Color.parseColor(colorItem.color))
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = colorItem.color,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp

            )
            Text(
                text = "Created at\n${formatDate(colorItem.time)}",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Start,
                fontSize = 14.sp
            )
        }
    }
}

fun generateRandomColor(): String {
    val random = java.util.Random()
    return String.format("#%06X", 0xFFFFFF and random.nextInt())
}

fun formatDate(timestamp: Long): String {
    val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return sdf.format(java.util.Date(timestamp))
}

data class ColorItem(val color: String, val time: Long)
