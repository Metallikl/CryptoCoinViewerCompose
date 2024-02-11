package br.com.dluche.cryptocoinviewercompose.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.dluche.cryptocoinviewercompose.data.model.entity.CryptoCoinEntity

@Dao
interface CryptoCoinDao {
    @Insert
    fun insertAll(coins: List<CryptoCoinEntity>)

    @Query("DELETE FROM crypto_coins")
    fun deleteAll()

    @Query("SELECT * FROM crypto_coins LIMIT :limit OFFSET :offset")
    fun getCoins(offset: Int = 0, limit: Int = 20): List<CryptoCoinEntity>

    @Query("SELECT * FROM crypto_coins WHERE :search IS NULL OR :search IS NOT NULL AND name like '%'||:search||'%' LIMIT :limit OFFSET :offset")
    fun getCoinsNextPage(search: String?, offset: Int, limit: Int = 20): List<CryptoCoinEntity>
}