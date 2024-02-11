package br.com.dluche.cryptocoinviewercompose.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.dluche.cryptocoinviewercompose.extentions.emptyString


@Entity(tableName = "crypto_coins")
data class CryptoCoinEntity(
    @PrimaryKey
    val id: String = String.emptyString(),
    @ColumnInfo(name = "active")
    val isActive: Boolean = false,
    @ColumnInfo(name = "new")
    val isNew: Boolean = false,
    val name: String = String.emptyString(),
    val rank: Int = 0,
    val symbol: String = String.emptyString(),
    val type: String = String.emptyString()
)
