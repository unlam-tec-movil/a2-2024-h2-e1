package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReplyButton(replyCount: Int, onReplyClicked: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onReplyClicked() }
    ) {
        Text("💬")
        Spacer(modifier = Modifier.width(4.dp))
        Text("$replyCount replies") 
    }
}

