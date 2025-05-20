package com.emo.haha.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun PagerCase() {
    val mState = rememberPagerState(pageCount = {
        4
    })
    HorizontalPager(state = mState) { page ->
        Text(
            text = "Page: $page", modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(
                        red = Random.nextInt(0, 255),
                        green = Random.nextInt(0, 255),
                        blue = Random.nextInt(0, 255)
                    )
                )
                .height(200.dp)
                .wrapContentHeight(), textAlign = TextAlign.Center
        )
    }
}