package com.example.habithero.presentation.MainScreens.CartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habithero.ui.theme.PurpleOfScreen

@Composable
fun CartScreen() {
    Box(modifier = Modifier.fillMaxSize().background(PurpleOfScreen)){
    Column(modifier = Modifier.fillMaxSize().background(PurpleOfScreen), verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.padding(17.dp))
        Text(text = "4")
    }
}}