package com.emo.haha.compose

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emo.haha.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BasicComponent() {
    val safeActivity = LocalActivity.current
    var username by rememberSaveable { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(R.mipmap.home_select),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 55.dp, start = 16.dp)
                .clickable {
                    safeActivity?.finish()
                })
        Image(
            painter = painterResource(R.mipmap.home_select),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 64.dp)
                .size(70.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.app_name),
            color = colorResource(R.color.black),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Box(
            modifier = Modifier
                .padding(top = 64.dp, start = 32.dp, end = 32.dp)
                .height(48.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
                .background(Color(0xFFEBEEF0)),
            contentAlignment = Alignment.CenterStart
        ) {
            val isHintVisible = username.isEmpty()
            if (isHintVisible) {
                Text(
                    text = stringResource(R.string.basic_text_tips),
                    fontSize = 16.sp,
                    color = Color(0xFFB6B6B6),
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
//            BasicTextField 不支持提示文字
            BasicTextField(
                value = username,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                onValueChange = {
                    username = it
                },
                textStyle = TextStyle(
                    color = Color(0xFF161718),
                    fontSize = 16.sp,
                ),
                singleLine = true,//单行
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                maxLines = 1,
                cursorBrush = SolidColor(Color(0xFFff0000))//光标颜色
            )
        }
        Box(modifier = Modifier
            .padding(top = 16.dp, start = 32.dp, end = 32.dp)
            .height(50.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null // 禁用水波纹
            ) {
                /* Toast
                     .makeText(
                         context, context.getString(R.string.basic_text_tips), Toast.LENGTH_SHORT
                     )
                     .show()*/
            }
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFFFF5F00).copy(0.9f), Color(0xFFFF995D))
                )
            ), contentAlignment = Alignment.CenterStart) {
            Text(
                text = stringResource(R.string.app_name),
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center)
            )

        }
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = Modifier.size(48.dp),  // 调整整体大小
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Blue,     // 选中状态颜色
                uncheckedColor = Color.Gray    // 未选中状态颜色
            ),
        )
        Text(text = buildAnnotatedString {
            withStyle(SpanStyle(color = Color.Black)) { append("文字变色加粗") }
            withStyle(
                SpanStyle(
                    color = Color.Blue, fontWeight = FontWeight.Medium
                )
            ) {
                append("《Jetpack Compose》")
            }
        }, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("one")
            Text("one")
            Text("one")
        }
        Row(
//            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFFFF5F00).copy(0.9f)),
        ) {
            Text("one")
            Spacer(modifier = Modifier.weight(1f))
            Text("two")
            Text("three")
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerticalList() {
    var itemCount by remember { mutableIntStateOf(5) }
    var isRefreshing by remember { mutableStateOf(false) }
    val state = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            delay(1000)
            itemCount += 5
            isRefreshing = false
        }
    }
    PullToRefreshBox(isRefreshing = isRefreshing, onRefresh = onRefresh, state = state) {
        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()//防止遮挡
                .navigationBarsPadding(), verticalArrangement = Arrangement.spacedBy(20.dp),//条目间距
            contentPadding = PaddingValues(top = 20.dp, bottom = 20.dp)//列表上下边距
        ) {
            items(itemCount) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 20.dp)
                        .background(Color.White)
                ) {
                    Text(
                        stringResource(R.string.title_activity_compose) + "-$index",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}