package com.leotrindade.newadvice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Login")
data class Login(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var email: String = "",
    var senha: String = ""
)