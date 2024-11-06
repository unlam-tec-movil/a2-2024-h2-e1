package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import coil.compose.AsyncImage

@Composable
fun TuitCard(
    tuit: Tuit,
    modifier: Modifier = Modifier,
    likePost: (it: Int) -> Unit,
) {
    Card(
        modifier = modifier,
    ) {
        Header(tuit = tuit)
        Box(modifier = Modifier.padding(8.dp)) {
            Column {
                Row {
                    Text(tuit.message, fontSize = 24.sp)
                }
            }
        }
        BottomOptions(tuit, like = { likePost(tuit.id) })
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
                    model = tuit.avatarUrl,
                    modifier =
                        Modifier
                            .clip(CircleShape)
                            .size(60.dp)
                            .width(60.dp),
                )
                Text(text = "@" + tuit.author, Modifier.padding(horizontal = 10.dp), fontWeight = FontWeight.Black)
            }
        }
    }
}

@Composable
fun BottomOptions(
    tuit: Tuit,
    like: (id: Int) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Button(
            onClick = { like(tuit.id) },
        ) {
            Icon(Icons.Outlined.FavoriteBorder, contentDescription = "like")
        }
        Button(
            onClick = { },
        ) {
            Icon(Icons.Outlined.Email, contentDescription = "comments")
        }

        Button(
            onClick = { },
        ) {
            Icon(Icons.Outlined.Share, contentDescription = "share")
        }
    }
}
