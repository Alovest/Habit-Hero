package com.example.habithero.presentation.MainScreens.CartScreen

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.core.graphics.component3
import androidx.core.graphics.component4
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.gif.AnimatedImageDecoder
import coil3.gif.GifDecoder
import com.example.habithero.R
import com.example.habithero.ui.theme.PurpleOfScreen
import com.example.habithero.ui.theme.backColor
import com.example.habithero.ui.theme.colorOfCard

@Composable
fun CartScreen() {
    val context = LocalContext.current
    val giflist = listOf(R.drawable.cat_2_phaza, R.drawable.cat)
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    add(AnimatedImageDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }.build()
    }
    Box(modifier = Modifier.fillMaxSize().background(backColor)){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Фазы котика:",
                style = MaterialTheme.typography.h4,
                color = Color.White
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(giflist) { uri ->
                Card(
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = colorOfCard)
                ) {
                    Text(text = when(uri){
                        R.drawable.cat_2_phaza -> "1"
                        R.drawable.cat -> "2"
                        else -> "0"
                    })
                    AsyncImage(
                        model = uri,
                        contentDescription = "phase",
                        imageLoader = imageLoader,
                        modifier = Modifier.padding(30.dp)
                    )
                }
            }
        }
    }
}}