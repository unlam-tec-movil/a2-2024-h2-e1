package ar.edu.unlam.mobile.scaffolding.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.db.dao.FavoriteUsersDao
import ar.edu.unlam.mobile.scaffolding.data.local.db.dao.LocalUserDao
import ar.edu.unlam.mobile.scaffolding.data.local.db.dao.TuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.FavoriteUserEntity
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.LocalUserEntity
import ar.edu.unlam.mobile.scaffolding.data.local.db.entity.SavedMessageEntity

@Database(
    entities = [LocalUserEntity::class, SavedMessageEntity::class, FavoriteUserEntity::class],
    version = 1,
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDao

    abstract fun tuitDao(): TuitDao

    abstract fun favoriteUsersDao(): FavoriteUsersDao
}
