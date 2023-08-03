package com.test.spinthename

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.OutlinedTextField

import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NamesScreen(onNextScreen: (List<String>) -> Unit) {
    var names by remember { mutableStateOf(mutableStateListOf<String>()) }
    var nameToAdd by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nameToAdd,
            onValueChange = { nameToAdd = it },
            label = { Text("Enter a name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nameToAdd.text.isNotBlank()) {
                    names.add(nameToAdd.text)
                    nameToAdd = TextFieldValue()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Name")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (names.size >= 10) {
                    onNextScreen(names)
                }
            },
            enabled = names.size >= 10,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next")
        }
    }
}

