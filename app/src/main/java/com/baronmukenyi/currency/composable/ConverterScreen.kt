package com.baronmukenyi.currency.composable

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.baronmukenyi.currency.models.Rates
import com.baronmukenyi.currency.ui.theme.Label_Color
import com.baronmukenyi.currency.ui.theme.Lovelo_Black
import com.baronmukenyi.currency.ui.theme.Orange_700
import com.baronmukenyi.currency.ui.theme.White_900
import com.baronmukenyi.currency.utils.Constants.Companion.APP_BAR_TITLE
import com.baronmukenyi.currency.utils.Constants.Companion.CURRENCY_CODES_LIST
import com.baronmukenyi.currency.utils.NetworkResult
import com.baronmukenyi.currency.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ConverterScreen(context: Context, mainViewModel: MainViewModel){

    val fromCurrencyCode = remember { mutableStateOf("USD") }
    val toCurrencyCode = remember { mutableStateOf("INR") }
    val amountValue = remember { mutableStateOf("") }
    val convertedAmount = remember { mutableStateOf("") }
    val singleConvertedAmount = remember { mutableStateOf("") }

    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    val isDarkMode:Boolean = isSystemInDarkTheme()

    var isFromSelected = true

    BottomSheetScaffold(
        sheetContent = {
                       Box(modifier = Modifier.fillMaxWidth()
                           .height(250.dp)) {
                           LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
                           ) {
                             items(CURRENCY_CODES_LIST){ item ->
                                 Text(
                                     text = "${item.currencyCode}\t ${item.countryName}",
                                     modifier = Modifier.padding(vertical = 10.dp)
                                         .clickable {
                                             if (isFromSelected){
                                                 fromCurrencyCode.value = item.currencyCode
                                             }else{
                                                 toCurrencyCode.value = item.currencyCode
                                             }
                                             scope.launch { scaffoldState.bottomSheetState.collapse() }
                                         }
                                 )
                             }
                           }
                       }
        },
        topBar = {
            TopAppBar(title = {
                Text(
                    text = APP_BAR_TITLE,
                    fontSize = 24.sp,
                    fontFamily = Lovelo_Black,
                    color = if(isDarkMode) White_900 else Orange_700,
                    style = TextStyle(
                        fontWeight = FontWeight.Black,
                        lineHeight = 30.sp,
                    )
                )
            },
                backgroundColor = if (isDarkMode) Color.Black else White_900,
                elevation = 0.dp
            )
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetElevation = 6.dp,
        sheetBackgroundColor = Color(0xFFF1F1F1),
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
    ){
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize()){
                Text(text = "From", color = Label_Color)
                Spacer(modifier = Modifier.padding(3.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clickable {
                        isFromSelected = true
                        scope.launch { scaffoldState.bottomSheetState.expand() }
                //
                }
                    .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),

                    contentAlignment = Alignment.CenterStart
                ){
                    Text(text = fromCurrencyCode.value, modifier = Modifier.padding(10.dp))
                }

            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "To", color = Label_Color)
            Spacer(modifier = Modifier.padding(3.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clickable {
                    isFromSelected = false
                    scope.launch { scaffoldState.bottomSheetState.expand() }
            //
            }
                .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),

                contentAlignment = Alignment.CenterStart
            ){
                Text(text = toCurrencyCode.value, modifier = Modifier.padding(10.dp))
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "amount", color = Label_Color)
                Text(text = fromCurrencyCode.value, color = Label_Color)
            }

            Spacer(modifier = Modifier.padding(3.dp))
            OutlinedTextField(value = amountValue.value, onValueChange = {amountValue.value = it},
            modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("0.00", style = MaterialTheme.typography.body1)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(onClick = {
                             mainViewModel.getExchangeRates(provideQueries(fromCurrencyCode.value))
                mainViewModel.exchangeRateResponse.observe(context as LifecycleOwner, {
                    response ->
                    run {
                        when (response) {
                            is NetworkResult.Success ->
                                response.data?.let {
                                    if (amountValue.value.isEmpty()) {
                                        amountValue.value = "1.00"
                                    }

                                    val toValue = getToValue(toCurrencyCode.value, it.rates)
                                    val amount = amountValue.value.toDouble()
                                    convertedAmount.value =
                                        "${getOutputString(amount * toValue)} ${toCurrencyCode.value}"
                                    singleConvertedAmount.value =
                                        "1 ${fromCurrencyCode} = ${getOutputString(toValue)} ${toCurrencyCode.value}"
                                }
                        }

                    }
                })
            },
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
                ){
                   Text("CONVERT", fontSize = 20.sp, color = White_900)
            }

            Spacer(modifier = Modifier.padding(30.dp))
            Text(text = convertedAmount.value,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 30.sp,
                color = Orange_700,
                style = TextStyle(
                    fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = singleConvertedAmount.value,
                modifier = Modifier.fillMaxWidth(),
                color = Label_Color,
                style = TextStyle(textAlign = TextAlign.Center)
            )
        }

    }
}

fun getOutputString(value: Double): String {
    return "%.2f".format(value)
}

fun getToValue(currencyCode: String, rates: Rates): Double {
    return when(currencyCode){
        "AUD" -> rates.aUD
        "BRL" -> rates.bRL
        "BGN" -> rates.bGN
        "CAD" -> rates.cAD
        "CYN" -> rates.cNY
        //"HRK" -> rates.hRK
        "CZK" -> rates.cZK
        //"DKK" -> rates.
        //"EUR" -> rates.
        "GBP" -> rates.gBP
        "HKD" -> rates.hKD
        "HUF" -> rates.hUF
        "ISK" -> rates.iSK
        "INR" -> rates.iNR
        "IDR" -> rates.iDR
        "ILS" -> rates.iLS
        //"JPY" -> rates.
        "KRW" -> rates.kRW
        "MYR" -> rates.mYR
        //"MXN" -> rates.mXN
        "NZD" -> rates.nZD
        "NOK" -> rates.nOK
        "PHP" -> rates.pHP
        "PLN" -> rates.pLN
        "RON" -> rates.rON
        //"RUB" -> rates.
        //"SGD" -> rates.
        "ZAR" -> rates.zAR
        "SEK" -> rates.sEK
        //"CHF" -> rates.c
        //"THB" -> rates.t
        "TRY" -> rates.tRY
        "USD" -> rates.uSD
        else -> 0.00

    }
}

fun provideQueries(from: String): Map<String, String> {
    val queries = HashMap<String, String>()
    queries["base"] = from
    return queries
}
