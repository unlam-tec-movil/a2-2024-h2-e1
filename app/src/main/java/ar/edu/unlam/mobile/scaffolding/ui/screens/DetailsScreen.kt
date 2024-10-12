package ar.edu.unlam.mobile.scaffolding.ui.screens

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.R.*


class DetailsScreen : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ProfileView()
        }
    }
}
@Composable
fun ProfileView(){
    ProfileTopBar()
    ProfileTuitList()
}
@Composable
fun ProfileTopBar(){
    Column(modifier = Modifier.background(Color.White).padding(2.dp)) {
        Imagenes()
        NombreUsuario()
        StatsBar()
        ProfileButtons {  }
    }
}

@Composable
fun Imagenes(){
    Image(
        painterResource(drawable.ic_launcher_foreground),"una imagen random",
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .clip(CircleShape)
            .background(Color.Gray)

    )
}

@Composable
fun NombreUsuario(){
        Text(
            text = "Username",
            modifier = Modifier.padding(20.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
                .background(Color.Gray)
                .padding(10.dp)
        )
}
@Composable
fun StatsBar(){
    Row (modifier = Modifier.fillMaxWidth()

        .padding(bottom = 15.dp)
    ) {
        Text(
            text="Seguidores",
            modifier = Modifier.wrapContentSize(Alignment.Center)
                .padding(start = 50.dp)
        )
        Text(
            text="Seguidos",
            modifier = Modifier.wrapContentSize(Alignment.Center)
                .padding(start = 50.dp)
        )
        Text(
            text="Tuits",
            modifier = Modifier.wrapContentSize(Alignment.Center)
                .padding(start = 50.dp)
        )

    }
}

@Composable
fun ProfileButtons(onClick: () -> Unit ){
    Row(modifier = Modifier.fillMaxWidth()
        .wrapContentSize(Alignment.Center)
    ){
        Button(onClick = { onClick() }){
            Text("Seguir")
        }
        Button(onClick = { onClick() }){
            Text("Compartir")
        }
        Button(onClick = { onClick() }){
            Text("Mensaje")
        }

    }
}

@Composable
fun ProfileTuitList() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(
            text = "Outlined",
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}


@Preview
@Composable
fun previewTextos(){
    ProfileView()
}


