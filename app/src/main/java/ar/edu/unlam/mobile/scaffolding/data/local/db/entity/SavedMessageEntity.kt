package ar.edu.unlam.mobile.scaffolding.data.local.db.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffolding.domain.tuit.models.SavedMessage

@Entity(tableName = "messages")
data class SavedMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String = "",
)

fun SavedMessageEntity.asModel() =
    SavedMessage(
        id = id,
        text = text,
    )

fun SavedMessage.asEntity() =
    SavedMessageEntity(
        text = text,
    )
