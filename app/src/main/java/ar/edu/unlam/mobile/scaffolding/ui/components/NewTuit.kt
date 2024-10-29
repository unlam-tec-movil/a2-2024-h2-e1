package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewTuit(
    goHome: () -> Unit,
    addTuit: () -> Unit,
    setNewMessage: (it: String) -> Unit,
    textMessage: String,
    storeTuit: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Escribe tu nuevo tuit:")
        TextField(
            value = textMessage,
            onValueChange = { setNewMessage(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("¿Qué estás pensando?") },
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(
                onClick = {
                    storeTuit()
                    goHome()
                },
                modifier = Modifier.weight(1f),
            ) {
                Text("Guardar")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { addTuit() },
                modifier = Modifier.weight(1f),
            ) {
                Text("Publicar")
            }
        }
    }
}
