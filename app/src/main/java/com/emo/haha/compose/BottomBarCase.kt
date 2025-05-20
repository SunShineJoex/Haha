package com.emo.haha.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarCase() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("主页", "喜欢", "福利", "设置")
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("主页")
        })
    }, bottomBar = {
        BottomNavigation(
            modifier = Modifier.navigationBarsPadding(),
            backgroundColor = Color(0xFFFFFFFF),
            contentColor = Color(0xFFFFFFFF)
        ) {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(icon = {
                    Icon(
                        Icons.Filled.Favorite, contentDescription = null
                    )
                },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index })
            }
        }
    }) {

    }

}