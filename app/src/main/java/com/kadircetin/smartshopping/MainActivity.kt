package com.kadircetin.smartshopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kadircetin.smartshopping.screens.AddItemScreen
import com.kadircetin.smartshopping.screens.DetailScreen
import com.kadircetin.smartshopping.screens.ItemList
import com.kadircetin.smartshopping.ui.theme.SmartShoppingTheme
import com.kadircetin.smartshopping.viewmodel.ItemViewModel

class MainActivity : ComponentActivity() {
    private val viewModel : ItemViewModel by viewModels<ItemViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SmartShoppingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = "list_screen"
                            ){
                            composable("list_screen"){
                                viewModel.getItemList()
                                val itemList by remember {viewModel.itemList}
                                ItemList(itemList,navController)
                            }

                            composable("add_item_screen"){
                                AddItemScreen { item ->
                                    viewModel.insertItem(item)
                                    navController.navigate("list_screen")
                                }
                            }

                            composable("details_screen/{itemId}",
                                arguments = listOf(
                                    navArgument("itemId"){
                                        type= NavType.StringType
                                    }
                                )){
                                val itemIdString= remember{it.arguments?.getString("itemId")}
                                viewModel.getItem(itemIdString?.toIntOrNull() ?: 1)
                                val selectedItem by remember { viewModel.selectedItem}
                                DetailScreen(selectedItem) {
                                    viewModel.deleteItem(selectedItem)
                                    navController.navigate("list_screen")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}