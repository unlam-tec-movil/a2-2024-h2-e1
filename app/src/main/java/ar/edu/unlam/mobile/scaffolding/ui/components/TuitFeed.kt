package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.Tuit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TuitFeed(
    tuits: List<Tuit>,
    likeEvent: (id: Tuit) -> Unit,
    // viewModel: NewTuitViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column {
            TopAppBar(
                title = { Text("Twitter Unlam") },
                modifier = Modifier.fillMaxWidth(),
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
            )
            var modifier = Modifier.fillMaxSize()

            LazyColumn(modifier.padding(top = 10.dp)) {
                // item { TweetComposer() }
                items(tuits) { tuit ->
                    TuitCard(likePost = likeEvent, tuit = tuit, modifier = Modifier.padding(1.dp))
                }
            }
        }
    }
}

@Composable
fun TuitProfile(
    tuits: List<Tuit>,
    likeEvent: (tuit: Tuit) -> Unit,
) {
    var modifier = Modifier.fillMaxSize()

    LazyColumn(modifier.padding(top = 10.dp)) {
        items(tuits) { tuit ->
            TuitCard(likePost = likeEvent, tuit = tuit, modifier = Modifier.padding(1.dp))
        }
    }
}
