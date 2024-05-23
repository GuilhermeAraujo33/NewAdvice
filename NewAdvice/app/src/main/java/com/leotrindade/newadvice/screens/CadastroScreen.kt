package com.leotrindade.newadvice.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leotrindade.newadvice.R
import com.leotrindade.newadvice.componentes.HabilidadesDropdown
import com.leotrindade.newadvice.database.repository.PerfilRepository
import com.leotrindade.newadvice.model.Perfil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

@Composable
fun CadastroAprendizScreen() {
    var nomeState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var temaState by remember { mutableStateOf("") }
    var habilidadeState by remember { mutableStateOf("") }
    var telefoneState by remember { mutableStateOf("") }
    var mentorState by remember { mutableStateOf(false) }
    var aprendizState by remember { mutableStateOf(false) }
    var context = LocalContext.current
    var perfilRepository = PerfilRepository(context)
    var listaState = remember { mutableStateOf<List<Perfil>>(emptyList())}

    Surface {
        Column {
            PerfilForm(
                nome = nomeState,
                senha = senhaState,
                email = emailState,
                tema = temaState,
                habilidade = habilidadeState,
                telefone = telefoneState,
                mentor = mentorState,
                aprendiz = aprendizState,
                onNomeChange = { nomeState = it },
                onSenhaChange = { senhaState = it },
                onEmailChange = { emailState = it },
                onTemaChange = { temaState = it },
                onHabilidadeChange = { habilidadeState = it },
                onTelefoneChange = { telefoneState = it },
                onMentorChange = { mentorState = it },
                onAprendizChange = { aprendizState = it },
                atualizar = {
                    CoroutineScope(Dispatchers.IO).launch {
                        listaState.value = perfilRepository.listarPerfis()
                    }
                }
            )

            LaunchedEffect(Unit) {
                listaState.value =  perfilRepository.listarPerfis()
            }

            PerfilList(listaState.value)
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilForm(
    nome: String,
    senha: String,
    email: String,
    tema: String,
    habilidade: String,
    telefone: String,
    mentor: Boolean,
    aprendiz: Boolean,
    onNomeChange: (String) -> Unit,
    onSenhaChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onTemaChange: (String) -> Unit,
    onHabilidadeChange: (String) -> Unit,
    onTelefoneChange: (String) -> Unit,
    onMentorChange: (Boolean) -> Unit,
    onAprendizChange: (Boolean) -> Unit,
    atualizar: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val perfilRepository = PerfilRepository(context)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(25.dp))

                Image(
                    painter = painterResource(id = R.drawable.pngwing2),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nome,
                    onValueChange = onNomeChange,
                    label = { Text("Seu nome") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = { Text("Seu email") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_email),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = senha,
                    onValueChange = onSenhaChange,
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = tema,
                    onValueChange = onTemaChange,
                    label = { Text("Tema desejado") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = habilidade,
                    onValueChange = onHabilidadeChange,
                    label = { Text("Habilidade") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = telefone,
                    onValueChange = onTelefoneChange,
                    label = { Text("Telefone") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                HabilidadesDropdown()

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(checked = mentor, onCheckedChange = onMentorChange)
                    Text(text = "Mentor")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(checked = aprendiz, onCheckedChange = onAprendizChange)
                    Text(text = "Aprendiz")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        scope.launch {
                            val perfil = Perfil(
                                id = 0,
                                nome = nome,
                                senha = senha,
                                email = email,
                                tema = tema,
                                habilidade = habilidade,
                                telefone = telefone,
                                isMentor = mentor,
                                isAprendiz = aprendiz
                            )
                            val perfil_obj = perfilRepository.salvar(perfil);
                            if (perfil_obj!= null){
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Cadastro realizado com sucesso!",
                                        actionLabel = null,
                                        withDismissAction = true,
                                        duration = SnackbarDuration.Short
                                    )

                                }
                                atualizar()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_new)),
                    border = BorderStroke(2.dp, Color(0xFFFF7B7B))
                ) {
                    Text(
                        text = stringResource(id = R.string.text_cadastrar),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
@Composable
fun PerfilList2(listaPerfilState: List<Perfil>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        for (perfil in listaPerfilState) {
            PerfilCardTeste(perfil)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun PerfilCardTeste(perfil: Perfil) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val perfilRepository = PerfilRepository(context)
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
            ) {
                Text(
                    text = perfil.nome,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = perfil.habilidade,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = if (perfil.isAprendiz)"Aprendiz" else "Mentor",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

            }
            IconButton(
                onClick = {
                    scope.launch {
                        val perfil = Perfil(
                            id = 0,
                            nome = perfil.nome,
                            senha = perfil.senha,
                            email = perfil.email,
                            tema = perfil.tema,
                            habilidade = perfil.habilidade,
                            telefone = perfil.telefone,
                            isMentor = perfil.isAprendiz,
                            isAprendiz = perfil.isAprendiz
                        )
                        perfilRepository.excluir(perfil)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Excluir"
                )
            }
        }
    }
}
