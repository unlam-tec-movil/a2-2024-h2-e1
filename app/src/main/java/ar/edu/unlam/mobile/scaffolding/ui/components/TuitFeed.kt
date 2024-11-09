package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit
import ar.edu.unlam.mobile.scaffolding.ui.screens.NewTuitViewModel

@Composable
fun TuitFeed(
    tuits: List<Tuit>,
    likeEvent: (id: Int) -> Unit,
    viewModel: NewTuitViewModel
) {
    var modifier = Modifier.fillMaxSize()
    LazyColumn(modifier.padding(top = 10.dp)) {
        items(tuits) { tuit ->
            TuitCard(likePost = likeEvent, tuit = tuit, modifier = Modifier.padding(1.dp), viewModel = viewModel)
        }
    }
}
