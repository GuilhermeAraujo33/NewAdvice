package com.leotrindade.newadvice.navegacao

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.leotrindade.newadvice.screens.CadastroAprendizScreen
import com.leotrindade.newadvice.screens.CadastroMentorScreen
import com.leotrindade.newadvice.screens.EscolhaCadastro
import com.leotrindade.newadvice.screens.HomeScreen
import com.leotrindade.newadvice.screens.LoginScreen
import com.leotrindade.newadvice.screens.MenuScreen

@Composable
fun NavegacaoConfig(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(navController)
        }
        composable("escolhaCadastro") {
            EscolhaCadastro(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("home") {
            HomeScreen()
        }
        composable("cadastroMentor") {
            CadastroMentorScreen()
        }
        composable(
            "cadastroAprendiz"){
            CadastroAprendizScreen()

        }
    }
}
