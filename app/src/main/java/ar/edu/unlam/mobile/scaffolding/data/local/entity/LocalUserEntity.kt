package ar.edu.unlam.mobile.scaffolding.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

@Entity(tableName = "users")
data class LocalUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val avatar_url: String = "",
)

fun LocalUserEntity.asModel() =
    User(
        avatar_url = avatar_url,
        email = email,
        name = name,
    )

fun User.asEntity() =
    LocalUserEntity(
        avatar_url = avatar_url,
        email = email,
        name = name,
    )
