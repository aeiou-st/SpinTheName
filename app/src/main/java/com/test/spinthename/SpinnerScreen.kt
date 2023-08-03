package com.test.spinthename

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.engage.common.datamodel.Image
import kotlin.random.Random

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SpinnerScreen(names: List<String>) {
    var rotationState by remember { mutableStateOf(0f) }
    var randomName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(200.dp).rotate(rotationState)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.spin),
                    contentDescription = "Circle",
                    modifier = Modifier.size(200.dp),
                )
            }
        }

        Button(
            onClick = {
                rotationState += 360f * (4 + Random.nextInt(7))
            }
        ) {
            Text("Spin")
        }

        Button(
            onClick = {
                if (names.isNotEmpty()) {
                    randomName = names.random()
                }
            },
            enabled = names.isNotEmpty(),
        ) {
            Text("Random")
        }

        if (randomName.isNotEmpty()) {
            Text(
                text = "Random Name: $randomName",
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
