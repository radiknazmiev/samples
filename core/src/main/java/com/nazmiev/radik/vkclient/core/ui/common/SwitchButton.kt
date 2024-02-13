package com.nazmiev.radik.vkclient.core.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nazmiev.radik.vkclient.core.http.models.User

@Composable
fun SwitchButton(text: String, checking: (isChecked: Boolean) -> Unit, isChecked: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text)
        Spacer(
            Modifier
                .weight(1f))
        Switch(checked = isChecked, onCheckedChange = checking)
    }
}

@Preview
@Composable
fun SwitchButtonComposablePreview() {
    Box(
        modifier= Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        SwitchButton(text = "Repeat the task", checking = {}, true)
    }
}