package com.decloudius.jmodupe.presentation.screens.beranda

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.decloudius.jmodupe.R
import com.decloudius.jmodupe.domain.model.User
import com.decloudius.jmodupe.presentation.components.AutoScrollCarousel
import com.decloudius.jmodupe.presentation.components.LayananButton
import com.decloudius.jmodupe.presentation.components.SquareMenuItem
import com.decloudius.jmodupe.presentation.theme.JMODupeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun BerandaScreen(viewModel: BerandaViewModel = koinViewModel()) {
    val user = viewModel.user.value
    BerandaScreenContent(user = user)
}


@Preview(showBackground = true)
@Composable
fun BerandaScreenPreview() {
    JMODupeTheme {
        BerandaScreenContent(
            user = User(
                id = 1,
                email = "test@example.com",
                password = "1223",
                name = "Bambang",
                ktp = "1234567890123456",
                phone = "081234567890"
            )
        )
    }
}

@Composable
fun BerandaScreenContent(user: User?) {
    val scrollState = rememberScrollState()

    val layananList = listOf(
        "Jaminan hari tua" to Color(0xFFEC3232),
        "Jaminan Kecelakaan Kerja" to Color(0xFF2196F3),
        "Jaminan Kematian" to Color(0xFFE91E63),
        "Jaminan Pensiun" to Color(0xFF4CAF50),
        "Jaminan Kehilangan Pekerjaan" to Color(0xFFFF9800)
    )

    val menuItems = listOf(
        "Info Program",
        "Bayar/Autodebit",
        "Sertakan",
        "Pengkinian Data",
        "Cek Saldo JHT",
        "Ajukan Klaim JHT",
        "Video Edukasi",
        "Perumahan Pekerja",
        "Bantuan",
        "Menu Lainnya"
    )

    val carouselImages = listOf(
        "https://picsum.photos/400/200?random=1",
        "https://picsum.photos/400/200?random=2",
        "https://picsum.photos/400/200?random=3"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 16.dp)
        ) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_logo_jmo),
                    contentDescription = "JMO Logo",
                    modifier = Modifier.height(32.dp),
                    contentScale = ContentScale.Fit
                )
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Greeting
            Text(
                text = "Hello, ${user?.name ?: "Pengguna"}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = MaterialTheme.colorScheme.onSurface
            )

            // Layanan buttons
            LayananButton(
                text = layananList[0].first,
                icon = Icons.Default.Star,
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                iconTint = layananList[0].second,
                isTheEnd = true
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                LayananButton(
                    text = layananList[1].first,
                    icon = Icons.Default.Star,
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    iconTint = layananList[1].second
                )
                LayananButton(
                    text = layananList[2].first,
                    icon = Icons.Default.Star,
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    iconTint = layananList[2].second,
                    isTheEnd = true
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                LayananButton(
                    text = layananList[3].first,
                    icon = Icons.Default.Star,
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    iconTint = layananList[3].second
                )
                LayananButton(
                    text = layananList[4].first,
                    icon = Icons.Default.Star,
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    iconTint = layananList[4].second,
                    isTheEnd = true
                )
            }

            // Layanan Lainnya label
            Text(
                text = "Layanan Lainnya",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.onSurface
            )

            // Grid menu
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(menuItems) { item ->
                    SquareMenuItem(
                        text = item,
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        onClick = { }
                    )
                }
            }

            // Informasi label
            Text(
                text = "Informasi",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                color = MaterialTheme.colorScheme.onSurface
            )

            // Carousel
            AutoScrollCarousel(
                imageUrls = carouselImages,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


