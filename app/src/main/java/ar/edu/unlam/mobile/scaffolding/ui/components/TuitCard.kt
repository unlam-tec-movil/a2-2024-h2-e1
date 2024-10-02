package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Box(modifier = Modifier.padding(8.dp)) {
            Column {
                Row {
                    Column {
                        AsyncImage(
                            model = tuit.avatar,
                            contentDescription = "Android Picture",
                            modifier = Modifier.size(50.dp),
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                    ) {
                        Text(tuit.authorName, fontSize = 16.sp)
                    }
                }
                Row {
                    Text(tuit.content, fontSize = 24.sp)
                }
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
            content = "Esto es un tuit de prueba!",
            avatar = "https://ih1.redbubble.net/image.1221593566.8336/mwo,x1000,ipad_2_snap-pad,750x1000,f8f8f8.jpg",
            likes = 0,
            liked = false,
            replies = 0,
            reply = { id -> },
        )
    TuitCard(tuit)
}
