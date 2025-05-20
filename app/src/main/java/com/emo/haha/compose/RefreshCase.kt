package com.emo.haha.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.king.ultraswiperefresh.NestedScrollMode
import com.king.ultraswiperefresh.UltraSwipeRefresh
import com.king.ultraswiperefresh.indicator.lottie.LottieRefreshFooter
import com.king.ultraswiperefresh.indicator.lottie.LottieRefreshHeader
import com.king.ultraswiperefresh.rememberUltraSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun UltraSwipeRefreshSample() {
    val state = rememberUltraSwipeRefreshState()
    var itemCount by remember { mutableIntStateOf(20) }
    val coroutineScope = rememberCoroutineScope()

    UltraSwipeRefresh(state = state,
        onRefresh = {
            coroutineScope.launch {
                state.isRefreshing = true
                // TODO 刷新的逻辑处理，此处的延时只是为了演示效果
                delay(500)
                itemCount = 20
                state.isRefreshing = false
            }
        },
        onLoadMore = {
            coroutineScope.launch {
                state.isLoading = true
                // TODO 加载更多的逻辑处理，此处的延时只是为了演示效果
                delay(500)
                itemCount += 20
                state.isLoading = false
            }
        },
        modifier = Modifier
            .background(color = Color(0x7FEEEEEE))
            .statusBarsPadding()
            .navigationBarsPadding(),
        headerScrollMode = NestedScrollMode.Translate,
        footerScrollMode = NestedScrollMode.Translate,
        headerIndicator = {
            LottieRefreshHeader(it)
        },
        footerIndicator = {
            LottieRefreshFooter(it)
        }) {
        LazyColumn(Modifier.background(color = Color.White)) {
            repeat(itemCount) {
                item {
                    Card(
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        border = BorderStroke(1.dp, Color(0xFFFFFF))
                    ) {
                        Text(
                            text = "列表Item${it + 1}",
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .height(200.dp)
                                .wrapContentHeight(),//ToDo 内容垂直居中
                            color = Color(0xFF333333),
                            fontSize = 16.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}
