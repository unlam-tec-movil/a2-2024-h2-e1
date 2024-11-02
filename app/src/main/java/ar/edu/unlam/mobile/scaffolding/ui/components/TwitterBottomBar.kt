package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TwitterBottomBar() {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Icon(Icons.Default.Home, "Home")
            Icon(Icons.Default.Search, "Search")
            Icon(Icons.Default.Notifications, "Notifications")
            Icon(Icons.Default.Email, "Messages")
        }
    }
}
