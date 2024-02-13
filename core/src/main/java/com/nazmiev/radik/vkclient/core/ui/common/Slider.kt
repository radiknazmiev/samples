package com.nazmiev.radik.vkclient.core.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nazmiev.radik.vkclient.core.R
import kotlin.math.roundToInt

@Composable
fun CustomSlider(
    position: Float,
    range: ClosedFloatingPointRange<Float>,
    steps: Int,
    text: String,
    onValueChange: (position: Float) -> Unit
) {
    Column {
        Slider(
            value = position,
            onValueChange = onValueChange,
            valueRange = range,
            steps = steps
        )
        Text(
            text = text
        )
    }
}

@Preview
@Composable
fun CustomSliderComposablePreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CustomSlider(position = 1F, range = 0F..7F, steps = 7, text = "days", onValueChange = {})
    }
}