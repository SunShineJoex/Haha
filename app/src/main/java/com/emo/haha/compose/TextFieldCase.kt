package com.emo.haha.compose

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
import androidx.compose.ui.unit.dp

@Composable
fun TextFiledCase(){
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()//防止顶部布局浸入状态栏
            .navigationBarsPadding()//防止底部布局浸入导航栏
            .background(Color(0xFFD3D3D3)), contentAlignment = Alignment.TopCenter
    ) {
        BasicTextField(value = text,
            onValueChange = {
                if (it.length < 12) {//限制数量
                    text = it
                }
            },
            cursorBrush = SolidColor(Color.Cyan),//光标颜色
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),//键盘类型
            singleLine = true,
            modifier = Modifier
                .padding(top = 30.dp)
                .background(Color.White, RoundedCornerShape(50.dp))
                .height(50.dp)
                .fillMaxWidth(),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp)
                ) {
                    Box(
                        modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()
                        if (text.isBlank()) {//提示文字
                            Text("请输入。。。", color = Color(0xFFD3D3D3))
                        }
                    }
                }
            })
    }
}
