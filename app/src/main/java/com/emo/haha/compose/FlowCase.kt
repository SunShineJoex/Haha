package com.emo.haha.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.emo.haha.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowCase() {
    LaunchedEffect (Unit){//Unit 执行一次 ，如果是一个可变的值，会多次执行
        launch {  }//内部可以执行协程代码
    }

    FlowRow(
        modifier = Modifier.padding(8.dp)
    ) {
        TextLayout("Price: High to Low")
        TextLayout("Avg rating: 4+")
        TextLayout("Free breakfast")
        TextLayout("Free cancellation")
        TextLayout("£50 pn")

    }
}

@Composable
fun ChipItem(text: String, onClick: () -> Unit = {}) {
    FilterChip(
        modifier = Modifier.padding(end = 9.dp),
        onClick = onClick,
        leadingIcon = {},
        border = BorderStroke(1.dp, Color(0xFF3B3A3C)),
        label = {
            Text(text)
        },
        selected = true
    )
}

@Composable
fun TextLayout(title: String) {
    Text(
        text = title,
        color = Color(0xFFFFFFFF),
        modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(R.color.teal_200))
            .padding(10.dp)
    )
}