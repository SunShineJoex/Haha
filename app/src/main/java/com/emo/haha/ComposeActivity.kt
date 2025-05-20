package com.emo.haha

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.emo.haha.compose.BottomBarCase
import com.emo.haha.compose.FlowCase
import com.emo.haha.compose.LazyColumnCase
import com.emo.haha.compose.LazyVerticalGridCase
import com.emo.haha.compose.LazyVerticalStaggeredGridCase
import com.emo.haha.compose.ListCase
import com.emo.haha.compose.PagerCase
import com.emo.haha.compose.TextFiledCase
import com.emo.haha.compose.UltraSwipeRefreshSample

class ComposeActivity : ComponentActivity() {

    private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
    private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
            ), navigationBarStyle = SystemBarStyle.auto(
                lightScrim = lightScrim,
                darkScrim = darkScrim,
            )
        )//全屏模式
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = false // 启用白色图标
            isAppearanceLightNavigationBars = true
        }
        setContent {
            LazyVerticalStaggeredGridCase()
        }
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
fun Greeting2() {

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview2() {
    LazyVerticalStaggeredGridCase()
}
