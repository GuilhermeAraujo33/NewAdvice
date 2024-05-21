package com.leotrindade.newadvice.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.leotrindade.newadvice.model.Perfil

@Dao
interface PerfilDao {

    @Insert
    fun salvarPerfil(perfil: Perfil): Long

    @Delete
    fun excluirPerfil(perfil: Perfil): Int

    @Update
    fun atualizarPerfil(perfil: Perfil): Int

    @Query("SELECT * FROM tbl_Perfil ORDER BY nome ASC")
    fun listarPerfis():List<Perfil>

    @Query("SELECT * FROM tbl_Perfil WHERE id = :id")
    fun buscarPerfilPeloId(id: Long): Perfil

    @Query("SELECT * FROM tbl_Perfil WHERE tema = :tema")
    fun buscarPerfilPelaExperiencia(tema: String): List<Perfil>

    @Query("SELECT * FROM tbl_Perfil WHERE habilidade = :habilidade")
    fun buscarPerfilPelaHabilidade(habilidade: String): List<Perfil>

    @Query("SELECT * FROM tbl_Perfil WHERE email = :email AND senha = :senha")
    fun validarPerfil(email: String, senha: String): Perfil
}
