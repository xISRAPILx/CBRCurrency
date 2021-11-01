package ru.israpil.cbrcurrency.view.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import dagger.hilt.android.AndroidEntryPoint
import ru.israpil.cbrcurrency.view.list.CurrencyListScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val colors = if(isSystemInDarkTheme()) darkColors() else lightColors()
            MaterialTheme(colors = colors) {
                Scaffold {
                    CurrencyListScreen()
                }
            }
        }
    }
}
