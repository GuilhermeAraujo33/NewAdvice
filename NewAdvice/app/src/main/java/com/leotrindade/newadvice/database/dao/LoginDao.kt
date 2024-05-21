package com.leotrindade.newadvice.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.leotrindade.newadvice.model.Login

@Dao
interface LoginDao {

    @Query("SELECT * FROM tbl_Login WHERE email = :email AND senha = :senha")
    fun validarLogin(email: String, senha: String): Login

}