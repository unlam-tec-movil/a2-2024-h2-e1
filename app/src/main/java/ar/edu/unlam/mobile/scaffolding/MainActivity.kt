package ar.edu.unlam.mobile.scaffolding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffolding.ui.components.BottomBar
import ar.edu.unlam.mobile.scaffolding.ui.screens.HomeScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.IsLoggedUIState
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoggedUserUIState
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoginScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.LoginViewModel
import ar.edu.unlam.mobile.scaffolding.ui.screens.ProfileScreen
import ar.edu.unlam.mobile.scaffolding.ui.screens.RegistrationScreen
import ar.edu.unlam.mobile.scaffolding.ui.theme.ScaffoldingV2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldingV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: LoginViewModel = hiltViewModel()) {
    // Controller es el elemento que nos permite navegar entre pantallas. Tiene las acciones
    // para navegar como naviegate y también la información de en dónde se "encuentra" el usuario
    // a través del back stack
    val logState: IsLoggedUIState by viewModel.loggedState.collectAsState()
    val controller = rememberNavController()

    when (logState.loggedUserUiState) {
        is LoggedUserUIState.NotLogged -> {
            NavHost(navController = controller, startDestination = "login") {
                // composable es el componente que se usa para definir un destino de navegación.
                // Por parámetro recibe la ruta que se utilizará para navegar a dicho destino.
                composable("login") {
                    // Home es el componente en sí que es el destino de navegación.
                    LoginScreen(controller, viewModel)
                }
                composable("register") {
                    // Home es el componente en sí que es el destino de navegación.
                    RegistrationScreen(controller, viewModel)
                }
            }

            // RegistrationScreen()
        }

        is LoggedUserUIState.Logged -> {
            Scaffold(
                bottomBar = { BottomBar(controller = controller) },
                floatingActionButton = {
                    IconButton(onClick = { controller.navigate("home") }) {
                        Icon(Icons.Filled.Home, contentDescription = "Add")
                    }
                },
            ) { paddingValue ->
                // NavHost es el componente que funciona como contenedor de los otros componentes que
                // podrán ser destinos de navegación.
                NavHost(navController = controller, startDestination = "home") {
                    // composable es el componente que se usa para definir un destino de navegación.
                    // Por parámetro recibe la ruta que se utilizará para navegar a dicho destino.
                    composable("home") {
                        // Home es el componente en sí que es el destino de navegación.
                        HomeScreen(modifier = Modifier.padding(paddingValue))
                    }
                    composable("profile") {
                        // Home es el componente en sí que es el destino de navegación.
                        ProfileScreen(modifier = Modifier.padding(paddingValue))
                    }
                }
            }
        }
    }
}
