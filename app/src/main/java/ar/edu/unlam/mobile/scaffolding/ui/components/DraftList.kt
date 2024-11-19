package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage

@Composable
fun DraftList(
    drafts: List<SavedMessage>,
    clickDraft: (draft: SavedMessage) -> Unit,
    deleteDraft: (draft: SavedMessage) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row {
        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp),
            textAlign = TextAlign.Center,
            text = "Borradores",
        )
    }
    Row {
        LazyColumn {
            items(drafts) { draft ->
                Row {
                    Surface(onClick = { clickDraft(draft) }) {
                        Card(Modifier.fillMaxWidth().padding(4.dp)) {
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Column(Modifier.padding(8.dp)) {
                                    Text(text = "Borrador: " + draft.id.toString())
                                    Text(draft.text)
                                }
                                Column {
                                    Surface(color = Color.Transparent, onClick = { deleteDraft(draft) }) {
                                        Icon(Icons.Filled.DeleteForever, contentDescription = "eliminar borrador")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
