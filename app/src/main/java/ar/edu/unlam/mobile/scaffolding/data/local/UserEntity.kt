import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffolding.domain.user.models.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val avatarUrl: String,
    val name: String,
    val email: String,
    val token: String,
)

fun UserEntity.asModel() =
    User(
        avatarUrl = avatarUrl,
        email = email,
        id = id.toUInt(),
        name = name,
        password = token,
    )

fun User.asEntity() =
    UserEntity(
        avatarUrl = avatarUrl,
        email = email,
        name = name,
        token = password,
    )
