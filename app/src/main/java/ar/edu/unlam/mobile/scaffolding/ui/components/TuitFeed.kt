package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit

@Composable
fun TuitFeed(tuits: List<Tuit>) {
    LazyColumn {
        items(tuits) { tuit ->
            TuitCard(tuit, modifier = Modifier.padding(1.dp))
        }
    }
}

@Preview
@Composable
fun TuitFeedPreview() {
    val tuits =
        listOf(
            Tuit(
                id = 1,
                authorName = "John Doe",
                content = "Esto es un tuit de prueba!",
                avatar = "https://ih1.redbubble.net/image.1221593566.8336/mwo,x1000,ipad_2_snap-pad,750x1000,f8f8f8.jpg",
                likes = 0,
                liked = false,
                replies = 0,
                reply = { id -> },
            ),
            Tuit(
                id = 2,
                authorName = "Jane Doe",
                content = "Otro tuit de prueba!",
                avatar = "https://ih1.redbubble.net/image.1221593566.8336/mwo,x1000,ipad_2_snap-pad,750x1000,f8f8f8.jpg",
                likes = 0,
                liked = false,
                replies = 0,
                reply = { id -> },
            ),
        )
    TuitFeed(tuits = tuits)
}
