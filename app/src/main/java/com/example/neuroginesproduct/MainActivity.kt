package com.example.neuroginesproduct

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.neuroginesproduct.ui.details.ProductDetailScreen
import com.example.neuroginesproduct.ui.lists.ProductListScreen
import com.example.neuroginesproduct.ui.theme.NeuroginesProductTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeuroginesProductTheme {
                var currentScreen by remember { mutableStateOf("list") }

                if (currentScreen == "list") {
                    ProductListScreen(
                        onProductClick = { _ ->
                            currentScreen = "detail"
                        }
                    )
                } else {
                    ProductDetailScreen(
                        onBackClick = {
                            currentScreen = "list"
                        }
                    )
                }
            }
        }
    }
}
