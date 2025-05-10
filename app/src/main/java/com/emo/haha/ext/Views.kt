package com.emo.haha.ext

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.widget.Checkable

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