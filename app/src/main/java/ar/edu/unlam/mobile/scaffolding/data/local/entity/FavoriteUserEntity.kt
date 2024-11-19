package ar.edu.unlam.mobile.scaffolding.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffolding.domain.favorites.model.FavoriteUser

@Entity(tableName = "favorites")
data class FavoriteUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    @ColumnInfo(name = "avatarUrl")
    val avatar_url: String = "",
)

fun FavoriteUserEntity.asModel() =
    FavoriteUser(
        id = id,
        avatarUrl = avatar_url,
        name = name,
    )

fun FavoriteUser.asEntity() =
    LocalUserEntity(
        avatar_url = avatarUrl,
        name = name,
    )
