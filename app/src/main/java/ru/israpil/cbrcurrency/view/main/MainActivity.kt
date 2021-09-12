package ru.israpil.cbrcurrency.view.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.darkColors
import dagger.hilt.android.AndroidEntryPoint
import ru.israpil.cbrcurrency.view.list.CurrencyListScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = darkColors()) { // TODO: 12.09.2021 Add DayNight theme
                Scaffold {
                    CurrencyListScreen()
                }
            }
        }
    }
}
