package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User
import coil.compose.AsyncImage

@Composable
fun UserDetails(user: User) {
    ProfileTopBar(user)
    // ProfileTuitList()
}

@Composable
fun ProfileTopBar(user: User) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp).height(160.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Imagenes(imageData = user.avatar_url)
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            NombreUsuario(username = user.name, email = user.email)
        }
    }
}

@Composable
fun Imagenes(imageData: String) {
    AsyncImage(
        contentDescription = "profile picture",
        contentScale = ContentScale.Crop,
        model = imageData,
        modifier =
            Modifier
                .clip(CircleShape)
                .size(150.dp),
    )
}

@Composable
fun NombreUsuario(
    username: String,
    email: String,
) {
    Card(
        modifier =
            Modifier
                .fillMaxWidth()
                .background(Brush.linearGradient(listOf(Cyan, Blue, Magenta)))
                .padding(start = 50.dp, top = 20.dp, bottom = 20.dp, end = 50.dp)
                .height(150.dp),
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = username,
                modifier =
                    Modifier
                        .padding(16.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style =
                    TextStyle(
                        brush =
                            Brush.linearGradient(
                                colors = listOf(Magenta, Cyan),
                            ),
                    ),
            )
            Text(
                text = email,
                modifier =
                    Modifier
                        .padding(16.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style =
                    TextStyle(
                        brush =
                            Brush.linearGradient(
                                colors = listOf(Cyan, Blue, Magenta),
                            ),
                    ),
                textAlign = TextAlign.Center,
            )
        }
    }
}
/*
@Composable
fun StatsBar() {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
    ) {
        Text(
            text = "Seguidores",
            modifier =
                Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(start = 50.dp),
        )
        Text(
            text = "Seguidos",
            modifier =
                Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(start = 50.dp),
        )
        Text(
            text = "Tuits",
            modifier =
                Modifier
                    .wrapContentSize(Alignment.Center)
                    .padding(start = 50.dp),
        )
    }
}

@Composable
fun ProfileButtons(onClick: () -> Unit) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
    ) {
        Button(onClick = { onClick() }) {
            Text("Seguir")
        }
        Button(onClick = { onClick() }) {
            Text("Compartir")
        }
        Button(onClick = { onClick() }) {
            Text("Mensaje")
        }
    }
}

@Composable
fun ProfileTuitList() {
    OutlinedCard(
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Outlined",
            modifier =
                Modifier
                    .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun previewTextos() {
    UserDetails(User("https://ih1.redbubble.net/image.1221593566.8336/mwo,x1000,ipad_2_snap-pad,750x1000,f8f8f8.jpg", "email@example", "juan"))
}
*/
