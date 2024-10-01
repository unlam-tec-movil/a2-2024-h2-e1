package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit

@Composable
fun TuitCard(tuit: Tuit) {
    Card {
        Box(modifier = Modifier.padding(8.dp)) {
            Column {
                Row {
                    Text(tuit.authorName, fontSize = 32.sp)
                }
                Row {
                    Text(tuit.content, fontSize = 16.sp)
                }
                AsyncImage(
                    model = image,
                    contentDescription = "Android Picture",
                )
            }
        }
    }
}

@Preview
@Composable
fun TuitCardPreview() {
    val tuit =
        Tuit(
            id = 1,
            authorName = "John Doe",
            content = "Hello, world!",
            avatar = "https://example.com/avatar.jpg",
            likes = 0,
            liked = false,
            replies = 0,
            reply = { id -> },
        )
    TuitCard(tuit)
}
