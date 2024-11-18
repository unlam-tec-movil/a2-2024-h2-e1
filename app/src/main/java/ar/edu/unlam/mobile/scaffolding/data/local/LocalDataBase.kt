package ar.edu.unlam.mobile.scaffolding.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.local.dao.LocalUserDao
import ar.edu.unlam.mobile.scaffolding.data.local.dao.TuitDao
import ar.edu.unlam.mobile.scaffolding.data.local.entity.LocalUserEntity
import ar.edu.unlam.mobile.scaffolding.data.local.entity.SavedMessageEntity

@Database(
    entities = [LocalUserEntity::class, SavedMessageEntity::class],
    version = 1,
)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDao

    abstract fun tuitDao(): TuitDao
}
