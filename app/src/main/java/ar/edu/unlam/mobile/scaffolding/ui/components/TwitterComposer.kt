package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TweetComposer(
    goHome: () -> Unit,
    addTuit: () -> Unit,
    setNewMessage: (it: String) -> Unit,
    textMessage: String,
    storeTuit: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier =
                Modifier
                    .size(40.dp)
                    .clip(CircleShape),
            color = Color.LightGray,
        ) {}

        Spacer(modifier = Modifier.width(12.dp))

        // Text field
        OutlinedTextField(
            value = textMessage,
            onValueChange = { setNewMessage(it) },
            modifier =
                Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
            placeholder = { Text("¿Qué está pasando?") },
        )
        // Tweet button
        Button(
            onClick = { addTuit() },
            modifier = Modifier.padding(start = 8.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Text("Publicar")
        }
    }
}
