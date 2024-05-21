package com.leotrindade.newadvice.database.repository

import android.content.Context
import com.leotrindade.newadvice.model.Login
import com.leotrindade.newadvice.database.dao.LoginDb


class LoginRepository(contexte: Context) {

    var db = LoginDb.getDatabase(contexte).loginDao()

    fun validarLogin(email : String, senha: String): Login {
        return db.validarLogin(email = email,senha = senha)
    }


}