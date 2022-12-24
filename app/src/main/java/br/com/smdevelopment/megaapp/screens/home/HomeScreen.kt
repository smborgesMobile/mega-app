package br.com.smdevelopment.megaapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.smdevelopment.megaapp.R
import com.google.accompanist.flowlayout.FlowRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val amountOfNumber = remember { mutableStateOf("6") }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_bar_title), style = MaterialTheme.typography.h6)
        })
    }) {
        Column(modifier = Modifier.fillMaxWidth()) {
            NumberGeneratorDropDown { amount ->
                amountOfNumber.value = amount
            }
            SubmitButton(viewModel, amountOfNumber)
            NumberList(viewModel)
        }
    }
}

@Composable
private fun SubmitButton(
    viewModel: HomeViewModel,
    amountOfNumber: MutableState<String>
) {
    Button(
        onClick = { viewModel.generateNumbers(amountOfNumber.value) },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.generate_numbers),
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun NumberGeneratorDropDown(onItemSelected: ((String) -> Unit)) {
    var expanded by remember { mutableStateOf(false) }
    var selectedNumber by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedNumber,
            onValueChange = {
                selectedNumber = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .clickable {
                    expanded = !expanded
                },
            label = {
                Text(text = stringResource(id = R.string.text_field_label))
            },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )
            },
            enabled = false,
            singleLine = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            possibleNumbers.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedNumber = label
                    onItemSelected(selectedNumber)
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
fun NumberList(viewModel: HomeViewModel) {
    val gameList = viewModel.numberList
    LazyColumn(
        modifier = Modifier.padding(top = 24.dp),
        content = {
            item {
                gameList.forEach {
                    MegaCard(item = it)
                }
            }
        })
}

@Composable
fun MegaCard(item: String) {
    val itemList = item.split(" ")
    Card(modifier = Modifier.fillMaxWidth()) {
        FlowRow(modifier = Modifier.padding(12.dp), mainAxisSpacing = 8.dp) {
            itemList.forEach { number ->
                GameCircle(number)
            }
        }
    }
}

@Composable
fun GameCircle(number: String) {
    val megaColor = colorResource(id = R.color.mega_green)
    Text(
        modifier = Modifier
            .padding(16.dp)
            .drawBehind {
                drawCircle(
                    color = megaColor,
                    radius = this.size.maxDimension
                )
            },
        text = number,
        style = MaterialTheme.typography.body2,
        color = Color.White,
        fontSize = 18.sp
    )
}