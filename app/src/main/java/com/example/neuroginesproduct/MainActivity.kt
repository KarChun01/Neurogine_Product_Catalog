package com.example.neuroginesproduct

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.neuroginesproduct.data.remote.ApiInstance
import com.example.neuroginesproduct.data.repo.ProductRepo
import com.example.neuroginesproduct.ui.details.ProductDetailScreen
import com.example.neuroginesproduct.ui.details.ProductDetailViewModel
import com.example.neuroginesproduct.ui.lists.ProductListScreen
import com.example.neuroginesproduct.ui.lists.ProductListViewModel
import com.example.neuroginesproduct.ui.theme.NeuroginesProductTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeuroginesProductTheme {
                var currentScreen by remember { mutableStateOf("list") }
                val repo = remember { ProductRepo(ApiInstance.api) }
                val listViewModel = remember { ProductListViewModel(repo) }
                val detailViewModel = remember { ProductDetailViewModel(repo) }


                if (currentScreen == "list") {
                    ProductListScreen(
                        viewModel = listViewModel,
                        onProductClick = { product ->
                            detailViewModel.loadProduct(product.id)
                            currentScreen = "detail"
                        }
                    )
                } else {
                    ProductDetailScreen(
                        viewModel = detailViewModel,
                        onBackClick = {
                            currentScreen = "list"
                        }
                    )
                }
            }
        }
    }
}
