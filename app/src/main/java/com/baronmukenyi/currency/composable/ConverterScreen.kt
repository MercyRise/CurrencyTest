package com.baronmukenyi.currency.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.baronmukenyi.currency.ui.theme.Label_Color
import com.baronmukenyi.currency.ui.theme.Lovelo_Black
import com.baronmukenyi.currency.ui.theme.Orange_700
import com.baronmukenyi.currency.ui.theme.White_900
import com.baronmukenyi.currency.utils.Constants.Companion.APP_BAR_TITLE

@ExperimentalMaterialApi
@Composable
fun ConverterScreen(){

    val fromCurrencyCode = remember { mutableStateOf("USD") }
    val toCurrencyCode = remember { mutableStateOf("INR") }
    val amountValue = remember { mutableStateOf("") }
    val convertedAmount = remember { mutableStateOf("") }
    val singleConvertedAmount = remember { mutableStateOf("") }

    val scaffoldState = rememberBottomSheetScaffoldState()

    val isDarkMode:Boolean = isSystemInDarkTheme()

    val isFromSelected = true

    BottomSheetScaffold(
        sheetContent = {},
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
                Box(modifier = Modifier.fillMaxWidth().height(50.dp).clickable {

                }
                    .border(1.dp, Color.Gray, RoundedCornerShape(5.dp)),

                    contentAlignment = Alignment.CenterStart
                ){
                    Text(text = fromCurrencyCode.value, modifier = Modifier.padding(10.dp))
                }

            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = "To", color = Label_Color)
            Spacer(modifier = Modifier.padding(3.dp))
            Box(modifier = Modifier.fillMaxWidth().height(50.dp).clickable {

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

            Button(onClick = {  },
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