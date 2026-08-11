package com.decloudius.jmodupe.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Beranda : BottomNavItem("beranda", "Beranda", Icons.Default.Home)
    data object Berita : BottomNavItem("berita", "Berita", Icons.AutoMirrored.Filled.List)
    data object KartuDigital : BottomNavItem("kartu_digital", "Kartu Digital", Icons.Default.AccountBox)
    data object ProfilSaya : BottomNavItem("profil_saya", "Profil Saya", Icons.Default.AccountCircle)
}
