package com.leotrindade.newadvice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_Perfil")
data class Perfil(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var nome: String = "",
    var senha: String= "",
    var email: String = "",
    var tema: String = "",
    var habilidade: String = "",
    var telefone: String = "",
    @ColumnInfo(name = "is_mentor") var isMentor: Boolean = false,
    @ColumnInfo(name = "is_aprendiz") var isAprendiz: Boolean = false
)

