package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import coil.compose.AsyncImage

@Composable
fun TuitCard(
    tuit: Tuit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Header(tuit = tuit)
        Box(modifier = Modifier.padding(8.dp)) {
            Column {
                Row {
                    Text(tuit.content, fontSize = 24.sp)
                }
            }
        }
        BottomOptions(tuit)
    }
}

@Composable
fun Header(tuit: Tuit) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    contentDescription = "profile picture",
                    contentScale = ContentScale.Crop,
                    model = tuit.avatar,
                    modifier =
                        Modifier
                            .clip(CircleShape)
                            .size(60.dp)
                            .width(60.dp),
                )
                Text(text = "@" + tuit.authorName, Modifier.padding(horizontal = 10.dp), fontWeight = FontWeight.Black)
            }
        }
        Box {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = tuit.authorName)
            }
        }
    }
}

@Composable
fun BottomOptions(tuit: Tuit) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(Icons.Outlined.FavoriteBorder, contentDescription = "like")
        Image(
            painterResource(R.drawable.baseline_sync_problem_24),
            contentDescription = "Shout!",
            contentScale = ContentScale.Crop,
        )
        Image(
            painterResource(R.drawable.reply),
            contentDescription = "reply",
            contentScale = ContentScale.Crop,
        )
        Icon(Icons.Outlined.CheckCircle, contentDescription = "save it for later")
    }


@Preview
@Composable
fun TuitCardPreview() {
    val tuit =
        Tuit(
            id = 1,
            authorName = "John Doe",
            content = "Esto es un tuit de prueba!",
            avatar = "https://ih1.redbubble.net/image.1221593566.8336/mwo,x1000,ipad_2_snap-pad,750x1000,f8f8f8.jpg",
            likes = 0,
            liked = false,
            replies = 0,
            reply = { id -> },
        )
    TuitCard(tuit)}
}
