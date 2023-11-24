package com.example.composecourseyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.composecourseyt.composables.BottomNavigationBar
import com.example.composecourseyt.composables.Navigation
import com.example.composecourseyt.data.BottomNavItem

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() //Shouldn't be on splash screen
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        items = listOf(
                            BottomNavItem(
                                name = "Home",
                                route = "main_screen",
                                icon = Icons.Default.Home
                            ),
                            BottomNavItem(
                                name = "Chat",
                                route = "chat",
                                icon = Icons.Default.Notifications,
                                badgeCount = 13
                            ),
                            BottomNavItem(
                                name = "Settings",
                                route = "settings",
                                icon = Icons.Default.Settings
                            ),
                        ),
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        })
                }
            ) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    Surface(color = Color(0xFF202020), modifier = Modifier.fillMaxSize()) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}