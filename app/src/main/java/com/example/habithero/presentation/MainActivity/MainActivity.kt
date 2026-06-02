package com.example.habithero.presentation.MainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.material3.Surface
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.habithero.presentation.Navigation.NavGraph.NavGraph
import com.example.habithero.ui.theme.HabitHeroTheme
import com.example.habithero.ui.theme.PurpleOfScreen
import com.example.habithero.ui.theme.backColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                    NavGraph()
            }
        }
    }
}
