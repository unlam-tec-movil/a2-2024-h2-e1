package ar.edu.unlam.mobile.scaffolding.data.local

import UserEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1,
)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}
