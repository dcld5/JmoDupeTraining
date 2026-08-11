package com.decloudius.jmodupe.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.decloudius.jmodupe.presentation.screens.beranda.BerandaScreen
import com.decloudius.jmodupe.presentation.screens.berita.BeritaScreen
import com.decloudius.jmodupe.presentation.screens.kartudigital.KartuDigitalScreen
import com.decloudius.jmodupe.presentation.screens.login.LoginScreen
import com.decloudius.jmodupe.presentation.screens.profil.ProfilScreen

@Composable
fun JMOApp() {
    val rootNavController = rememberNavController()

    NavHost(navController = rootNavController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    rootNavController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("main") {
            MainScreen(
                onLogout = {
                    rootNavController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun MainScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val bottomNavItems = listOf(
        BottomNavItem.Beranda,
        BottomNavItem.Berita,
        BottomNavItem.KartuDigital,
        BottomNavItem.ProfilSaya
    )

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFFFFFFF)) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Beranda.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Beranda.route) { BerandaScreen() }
            composable(BottomNavItem.Berita.route) { BeritaScreen() }
            composable(BottomNavItem.KartuDigital.route) { KartuDigitalScreen() }
            composable(BottomNavItem.ProfilSaya.route) {
                ProfilScreen(onLogout = onLogout)
            }
        }
    }
}
