package com.emo.haha.ext

import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.WindowInsets
import android.widget.Checkable
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentActivity

/**
 * @author: SunshineJoex
 * @e-mail: sunshinejoex@gmail.com
 * @date:   2025/3/28 17:18
 * @description:
 */
private const val TAG = 1766613352

var <T : View> T.lastClickTime: Long
    set(value) = setTag(TAG, value)
    get() = getTag(TAG) as? Long ?: 0

inline fun <T : View> T.singleClick(time: Long = 500L, crossinline block: (T) -> Unit) {
    setOnClickListener {
        checkSingle(time, block)
    }
}

inline fun <T : View> T.checkSingle(time: Long = 500L, crossinline block: (T) -> Unit) {
    val currentTimeMillis = System.currentTimeMillis()
    if (currentTimeMillis - lastClickTime > time || this is Checkable) {
        lastClickTime = currentTimeMillis
        block(this)
    }
}

fun <T : View> T.singleClick(time: Long = 500L, listener: View.OnClickListener) {
    setOnClickListener {
        checkSingle(time, listener)
    }
}

fun <T : View> T.checkSingle(time: Long = 500L, listener: View.OnClickListener) {
    val currentTimeMillis = System.currentTimeMillis()
    if (currentTimeMillis - lastClickTime > time || this is Checkable) {
        lastClickTime = currentTimeMillis
        listener.onClick(this)
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.setVisible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * Set view invisible
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * Set view gone
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * dp->px
 */
val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )

/**
 * sp-px
 */
val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
    )

/**
 * statusBars add topPadding
 */
fun View.statusBarsPadding() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val navigationBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(navigationBars.left, navigationBars.top, navigationBars.right, 0)
        insets
    }
}


/**
 * navigationBarsPadding  add bottomPadding
 */
fun View.navigationBarsPadding() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val navigationBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(navigationBars.left, 0, navigationBars.right, navigationBars.bottom)
        insets
    }
}

/**
 * statusBars、navigationBarsPadding  add topPadding、bottomPadding
 */
fun View.systemBarsPadding() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val navigationBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(
            navigationBars.left,
            navigationBars.top,
            navigationBars.right,
            navigationBars.bottom
        )
        insets
    }
}

/**
 * darkContent true:暗色字体
 * decorFitsSystemWindows false:进入状态栏
 */
fun FragmentActivity.immersiveStatusBar(
    darkContent: Boolean = true,
    decorFitsSystemWindows: Boolean = true,
    @ColorInt color: Int = Color.TRANSPARENT
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) { // Android 15+
        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
            view.setBackgroundColor(color)
            // Adjust padding to avoid overlap
            view.setPadding(0, statusBarInsets.top, 0, 0)
            insets
        }
    } else {
        // For Android 14 and below
        window.statusBarColor = color
    }
    val insertControllerCompat = WindowCompat.getInsetsController(window, window.decorView)
    WindowCompat.setDecorFitsSystemWindows(window, decorFitsSystemWindows)
    insertControllerCompat.isAppearanceLightStatusBars = darkContent
}

