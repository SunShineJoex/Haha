package com.emo.haha.compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.random.Random

val datas = mutableListOf(
    "Column verticalScroll()",
    "LazyColumn",
    "LazyRow",
    "LazyVerticalGrid",
    "LazyVerticalStaggeredGrid",
    "Sticky headers (experimental)"
)

@Composable
fun ListCase() {
    val context = LocalContext.current
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        repeat(4) {
            datas.forEach {
                Text(
                    it,
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .height(50.dp)
                        .background(Color(0xFFBBBBBB))
                        .wrapContentHeight()
                        .clickable(
                            //TODO 禁用水波纹
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            Toast
                                .makeText(context, "click", Toast.LENGTH_SHORT)
                                .show()
                        },
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun LazyColumnCase() {
    LazyColumn(contentPadding = PaddingValues(20.dp)) {
        repeat(4) {
            items(datas.size) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 20.dp)
                        .background(
                            Color(
                                red = Random.nextInt(0, 255),
                                green = Random.nextInt(0, 255),
                                blue = Random.nextInt(0, 255)
                            )
                        )
                ) {

                }
            }
        }
    }
}

@Composable
fun LazyVerticalGridCase() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        repeat(40) {
            items(datas.size) {
                Box(
                    Modifier
                        .size(100.dp)
                        .background(
                            Color(
                                red = Random.nextInt(0, 255),
                                green = Random.nextInt(0, 255),
                                blue = Random.nextInt(0, 255)
                            )
                        )
                ) {

                }
            }
        }
    }
}

@Composable
fun LazyVerticalStaggeredGridCase() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        repeat(40) {
            items(datas.size) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(Random.nextInt(100, 300).dp)
                        .background(
                            Color(
                                red = Random.nextInt(0, 255),
                                green = Random.nextInt(0, 255),
                                blue = Random.nextInt(0, 255)
                            )
                        )
                ) {
                }
            }
        }
    }
}