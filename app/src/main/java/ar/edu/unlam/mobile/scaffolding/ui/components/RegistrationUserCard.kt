package ar.edu.unlam.mobile.scaffolding.ui.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
@Preview
fun RegistrationUserCard(viewModel: RegistrationViewModel = viewModel()) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            val gradientColors = listOf(Cyan, Blue, Magenta)
            val email by viewModel.email
            val name by viewModel.name
            val pass by viewModel.pass

            Text(
                text = stringResource(R.string.registrarse),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                modifier = Modifier.width(200.dp),
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text(stringResource(R.string.correo)) }
            )

            OutlinedTextField(
                value = name,
                onValueChange = { viewModel.onNameChange(it) },
                label = { Text(stringResource(R.string.nombre)) }
            )

            OutlinedTextField(
                value = pass,
                onValueChange = { viewModel.onPassChange(it) },
                label = { Text(stringResource(R.string.contrase_a)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(onClick = { viewModel.onSubmit() }) {
                Text(stringResource(R.string.enviar))
            }
        }
    }
}
/*fun onClick() {
    TODO("Not yet implemented")
}*/
