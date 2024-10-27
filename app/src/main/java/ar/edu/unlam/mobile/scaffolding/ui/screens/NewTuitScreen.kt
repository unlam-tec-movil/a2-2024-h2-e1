package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.unlam.mobile.scaffolding.ui.components.NewTuit

@Composable
fun NewTuitScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column {
        /*
        Button(
            onClick = { navController.navigate("home")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
        Text("Volver")
        }

         */

        IconButton(onClick = { navController.navigate("home") }) { // Cambiamos a "new_tuit"
        Icon(Icons.Filled.Close, contentDescription = "Cerrar")
        }

        NewTuit()
    }
}
