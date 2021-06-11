package com.baronmukenyi.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.ui.core.setContent
import com.baronmukenyi.currency.composable.ConverterScreen
import com.baronmukenyi.currency.ui.theme.CurrencyTheme

class MainActivity : AppCompatActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyTheme {
                ConverterScreen()
            }
        }
    }
}