package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TwitterFAB() {
    FloatingActionButton(
        onClick = { /* TODO */ },
        modifier = Modifier.size(56.dp),
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "New Tweet",
            tint = Color.White,
        )
    }
}
