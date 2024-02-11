package br.com.dluche.cryptocoinviewercompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.dluche.cryptocoinviewercompose.data.database.dao.CryptoCoinDao
import br.com.dluche.cryptocoinviewercompose.data.model.entity.CryptoCoinEntity


@Database(entities = [CryptoCoinEntity::class], version = 1)
abstract class CryptoCoinDb: RoomDatabase() {
    abstract fun coinsDao(): CryptoCoinDao
}