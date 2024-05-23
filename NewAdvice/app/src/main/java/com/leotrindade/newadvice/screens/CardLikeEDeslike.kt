package com.leotrindade.newadvice.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leotrindade.newadvice.R
import com.leotrindade.newadvice.database.repository.PerfilRepository
import com.leotrindade.newadvice.model.Perfil

/*@Composable
fun CardLikeEDeslike() {
    var likes by remember { mutableStateOf(0) }
    var dislikes by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "nome",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "habilidade",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Aprendiz",
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { likes++ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = "Like",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "($likes)")
                }

                Button(onClick = { dislikes++ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dislike),
                        contentDescription = "Dislike",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "($dislikes)")
                }
            }
        }
    }
}*/

@Composable
fun CardLikeEDeslike(perfil: Perfil) {
    val context = LocalContext.current
    val perfilRepository = PerfilRepository(context)

    var likes by remember { mutableStateOf(0) }
    var dislikes by remember { mutableStateOf(0) }
    var userAction by remember { mutableStateOf<UserAction?>(null) } // Estado para armazenar a ação do usuário
    var showDialog by remember { mutableStateOf(false) } // Estado para controlar a exibição do dialog
    var pendingAction by remember { mutableStateOf<UserAction?>(null) } // Estado para armazenar a ação pendente
    var listaState = remember { mutableStateOf<List<Perfil>>(emptyList())}
    
    // Função para processar a ação pendente
    fun processPendingAction() {
        pendingAction?.let { action ->
            when (action) {
                UserAction.LIKED -> {
                    when (userAction) {
                        UserAction.LIKED -> {
                            likes--
                            userAction = null
                        }
                        UserAction.DISLIKED -> {
                            dislikes--
                            likes++
                            userAction = UserAction.LIKED
                        }
                        null -> {
                            likes++
                            userAction = UserAction.LIKED
                        }
                    }
                }
                UserAction.DISLIKED -> {
                    when (userAction) {
                        UserAction.LIKED -> {
                            likes--
                            dislikes++
                            userAction = UserAction.DISLIKED
                        }
                        UserAction.DISLIKED -> {
                            dislikes--
                            userAction = null
                        }
                        null -> {
                            dislikes++
                            userAction = UserAction.DISLIKED
                        }
                    }
                }
            }
            pendingAction = null
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "DEU MATCH!") },
            text = { Text("Gostaria de iniciar uma conversa?") },
            confirmButton = {
                Button(
                    onClick = {
                        processPendingAction()
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_new)),
                    border = BorderStroke(2.dp, Color(0xFFFF7B7B))
                ) {
                    Text("Conversar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_new)),
                    border = BorderStroke(2.dp, Color(0xFFFF7B7B))
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = perfil.nome,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = perfil.tema,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = if (perfil.isAprendiz)"Aprendiz" else "Mentor",
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        pendingAction = UserAction.LIKED
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_new)),
                    border = BorderStroke(2.dp, Color(0xFFFF7B7B))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_like),
                        contentDescription = "Like",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "($likes)")
                }

                Button(
                    onClick = {
                        pendingAction = UserAction.DISLIKED
                        showDialog = true
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_new)),
                    border = BorderStroke(2.dp, Color(0xFFFF7B7B))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dislike),
                        contentDescription = "Dislike",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "($dislikes)")
                }
            }
        }
    }
}

enum class UserAction {
    LIKED,
    DISLIKED
}



@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(72.dp))

        Image(
            painter = painterResource(id = R.drawable.pngwing2),
            contentDescription = null,
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Escolha uma opção",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
