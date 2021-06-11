package com.baronmukenyi.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.EditorModel
import androidx.ui.core.setContent
import com.baronmukenyi.currency.composable.ConverterScreen
import com.baronmukenyi.currency.ui.theme.CurrencyTheme
import com.baronmukenyi.currency.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyTheme {
                mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                ConverterScreen(this, mainViewModel)
            }
        }
    }
}