package com.leotrindade.newadvice.database.repository

import android.content.Context
import android.util.Log
import com.leotrindade.newadvice.model.Perfil
import com.leotrindade.newadvice.database.dao.PerfilDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PerfilRepository(context: Context) {

    private val db = PerfilDb.getDatabase(context).perfilDao()

    suspend fun salvar(perfil: Perfil): Long {
        return try {
            withContext(Dispatchers.IO) {
                db.salvarPerfil(perfil)
            }
        } catch (e: Exception) {
            Log.e("PerfilRepository", "Erro ao salvar perfil", e)
            0L
        }
    }

    suspend fun atualizar(perfil: Perfil): Int {
        return try {
            withContext(Dispatchers.IO) {
                db.atualizarPerfil(perfil)
            }
        } catch (e: Exception) {
            Log.e("PerfilRepository", "Erro ao atualizar perfil", e)
            0
        }
    }

    fun excluir(perfil: Perfil){
        CoroutineScope(Dispatchers.IO).launch {
            db.excluirPerfil(perfil)
        }
    }


    fun buscarPerfilPeloId(id:Long){
        CoroutineScope(Dispatchers.IO).launch {
            db.buscarPerfilPeloId(id)
        }
    }
    fun validarPerfil(email: String, senha: String){
        CoroutineScope(Dispatchers.IO).launch {
            db.validarPerfil(email,senha)
        }
    }


    suspend fun listarPerfis(): List<Perfil> {
        return withContext(Dispatchers.IO) {
            db.listarPerfis()
        }
    }
}
