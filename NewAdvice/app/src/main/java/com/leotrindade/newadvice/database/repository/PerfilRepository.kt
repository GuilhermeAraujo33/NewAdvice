package com.leotrindade.newadvice.database.repository

import android.content.Context
import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
    suspend fun validarPerfil(email: String, senha: String): Perfil?{
        return try {
            withContext(Dispatchers.IO) {
                db.validarPerfil(email, senha)
            }
        } catch (e: Exception) {
            println("Erro ao validar o perfil: ${e.message}")
            null
        }
    }


    suspend fun listarPerfis(): List<Perfil> {
        return withContext(Dispatchers.IO) {
            db.listarPerfis()
        }
    }
}
