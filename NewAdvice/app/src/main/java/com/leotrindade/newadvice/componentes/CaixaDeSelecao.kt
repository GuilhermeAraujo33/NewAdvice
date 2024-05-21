package com.leotrindade.newadvice.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabilidadesDropdown() {
    var habilidades by remember { mutableStateOf(listOf("Kotlin", "Java", "Python", "JavaScript")) }
    var expanded by remember { mutableStateOf(false) }
    var selectedHabilidade by remember { mutableStateOf("") }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            value = selectedHabilidade,
            onValueChange = { selectedHabilidade = it },
            label = { Text("Linguagem") },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            habilidades.forEach { habilidade ->
                DropdownMenuItem(
                    text = { Text(text = habilidade) },
                    onClick = {
                        selectedHabilidade = habilidade
                        expanded = false
                    }
                )
            }
        }
    }
}
