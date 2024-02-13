package com.nazmiev.radik.vkclient.core.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleSelectDialog(
    items: List<Pair<Int, String>>,
    dialogTitle: String,
    submitButtonText: String,
    selectedItem: Pair<Int, String>,
    onSubmitButtonClick: (Pair<Int, String>) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedOption by remember {
        mutableStateOf(selectedItem)
    }

    AlertDialog(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp)),
        onDismissRequest = { onDismissRequest.invoke() }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .width(300.dp),
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = dialogTitle,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    fontSize = TextUnit(value = 16F, type = TextUnitType.Sp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn {
                    items(items = items) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent)
                                .clickable {
                                    selectedOption = it
                                },
                        ) {
                            Text(
                                text = it.second,
                                fontSize = TextUnit(value = 14F, type = TextUnitType.Sp)
                            )
                            Spacer(
                                Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            )
                            RadioButton(
                                selected = selectedOption == it,
                                onClick = { selectedOption = it },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.secondary
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { onSubmitButtonClick.invoke(selectedOption) },
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = submitButtonText)
                }
            }
        }
    }
}

@Preview
@Composable
fun SingleSelectDialogPreview() {
    SingleSelectDialog(
        items = listOf(Pair(0, "Настрока 1"), Pair(1, "Настройка 2")),
        dialogTitle = "Выберите настройку",
        submitButtonText = "Выбрать",
        selectedItem = Pair(0, "Настрока 1"),
        onSubmitButtonClick = { setting: Pair<Int, String> -> },
        onDismissRequest = {}
    )
}