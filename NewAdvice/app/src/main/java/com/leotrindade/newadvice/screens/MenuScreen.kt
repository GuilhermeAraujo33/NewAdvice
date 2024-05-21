package com.leotrindade.newadvice.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.leotrindade.newadvice.R

@SuppressLint("ResourceType")
@Composable
fun MenuScreen(navController: NavHostController) {
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

        Spacer(modifier = Modifier.height(160.dp))

        Text(
            text = "Advice",
            color = Color.Black,
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(196.dp)
        )

        Spacer(modifier = Modifier.height(52.dp))

        Text(
            text = stringResource(id = R.string.terms_and_conditions_text),
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(274.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = {
                navController.navigate("cadastroAprendiz")
            },
            modifier = Modifier
                .width(248.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_new)),
            border = BorderStroke(2.dp, Color(0xFFFF7B7B))
        ) {
            Text(
                text = stringResource(id = R.string.text_cadastro),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("login")
            },
            modifier = Modifier
                .width(248.dp)
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.color_new)),
            border = BorderStroke(2.dp, Color(0xFFFF7B7B))

        ) {
            Text(
                text = stringResource(id = R.string.text_login),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
