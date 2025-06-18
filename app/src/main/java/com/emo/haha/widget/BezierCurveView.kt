package com.test.demo_test

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class BezierCurveView @JvmOverloads constructor(context: Context, attrs: AttributeSet): View(context, attrs) {

    private val mList: MutableList<String> = mutableListOf("VIP1", "VIP2", "VIP3", "VIP4", "VIP5", "VIP6", "VIP7")

    private val paint: Paint = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    private val textPaint: Paint = Paint().apply {
        color = Color.BLACK
        textSize = 50f
        isAntiAlias = true
    }

    private val path: Path = Path()

    private var lastX = 0f
    private var lastY = 0f
    private var scrollOffset = 0f // 用来保存当前的滚动偏移

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val startX = 0f
        val startY = height / 2f
        val endX = width.toFloat()
        val endY = height / 2f
//        Log.e("BezierCurveView", "width: $width , height: $height")

        // 控制点位置
        val controlX = width / 2f
        // 向上
//        val controlY = height / 3f
        // 向下
        val controlY = height * 2f / 3f
//        Log.e("BezierCurveView", "controlX: $controlX , controlY: $controlY")

        path.reset()
        path.moveTo(startX, startY)
        path.quadTo(controlX, controlY, endX, endY)
    }

    data class Coordinate(val x: Int, val y: Int)

    private val textPositions = mutableMapOf<Int, Coordinate>()
    private val allPositions = mutableMapOf<Int, Coordinate>()
    private var isPositionsInitialized = false
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制二次贝塞尔曲线
        canvas.drawPath(path, paint)

        // 获取路径的长度
        val pathMeasure = PathMeasure(path, false)
        val totalLength = pathMeasure.length
        drawCenter(pathMeasure, totalLength, canvas)

        val gap = totalLength / (mList.size + 1)  // 计算字之间的间距
//        var distance = totalLength / 2f - gap * (mList.size / 2) + scrollOffset
        var distance = totalLength / 2f + scrollOffset //初始坐标从中心点开始

        mList.forEachIndexed { index, text ->
            // 计算路径的中点位置
            val position = FloatArray(2)
            pathMeasure.getPosTan(distance, position, null)

            val x = position[0]
            val y = position[1] + 30.toPx()

            allPositions[index] = Coordinate(x = x.toInt(), y = y.toInt())
            if (textPositions.size != mList.size) {
                textPositions[index] = Coordinate(x = x.toInt(), y = y.toInt())
            }

            // 文字相关
            drawTextAndBg(canvas, text, x, y, textPaint, index)

            //文本之间的距离
            val extraGap = 80
            distance += gap + extraGap
        }
    }

    private fun drawTextAndBg(canvas: Canvas, text: String, x: Float, y: Float, textPaint: Paint, index: Int) {
        val textWidth = textPaint.measureText(text)
//            Log.e("BezierCurveView", "textWidth: $textWidth , scrollOffset: $x")

        val padding = 10f
        val rectLeft = x - textWidth / 2 - padding
        val rectTop = y - textPaint.textSize - padding
        val rectRight = x + textWidth / 2 + padding
        val rectBottom = y + padding

        val isInCenter = currentIndex == index
        val backgroundColor = if (isInCenter) Color.RED else Color.TRANSPARENT
        val backgroundPaint = Paint().apply {
            color = backgroundColor
            style = Paint.Style.FILL
        }
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, backgroundPaint)

        val alpha = when {
            x > 0 && textWidth + x <= 220 -> 20
            x <= 0 -> 0
            x.toInt() == width && abs(scrollOffset).toInt() != 0 -> 0
            x > 0 && width - x <= textWidth -> 20
            else -> 255
        }
        textPaint.alpha = alpha
        canvas.drawText(text, x - textPaint.measureText(text) / 2, y, textPaint)
    }

    private var currentIndex = 0
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.x - lastX
                scrollOffset += dx

                // 限制水平滑动距离在屏幕内
                val maxOffset = width.toFloat() / 2
                val minOffset = -calculateTotalTextSize() - width.toFloat() / 2
                scrollOffset = scrollOffset.coerceIn(minOffset, maxOffset)
//                Log.e("BezierCurveView", "maxOffset: $maxOffset , minOffset: $minOffset")

                // 更新上次的位置
                lastX = event.x
                lastY = event.y

                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                adjustToCenter()
            }
        }
        return true
    }

    private fun adjustToCenter() {
        // 找到与 scrollOffset 最近的坐标
        var closestIndex = -1
        var minDifference = 10000000

        val centerX = textPositions[0]?.x ?: (width / 2)
        allPositions.forEach { (index, coor) ->
//            Log.e("BezierCurveView", "滑动后的坐标: (${coor.x} , ${coor.y})")
            val difference = abs(coor.x - centerX)
            if (difference < minDifference) {
                minDifference = difference
                closestIndex = index  // 更新最接近的索引
            }
        }

        Log.e("BezierCurveView", "找到最近的closestIndex: $closestIndex")
        if (closestIndex != -1 && closestIndex < allPositions.values.size) {
            currentIndex = closestIndex

            val closestTextPosition = allPositions[closestIndex]
            closestTextPosition?.let { text ->
                val targetX = text.x
                val targetScrollOffset = centerX - targetX
                scrollOffset += targetScrollOffset.toFloat()
                invalidate()
            }
        }
    }

    private fun calculateTotalTextSize(): Float {
        var totalSize = 0f
        var space = 0f
        mList.forEach { text ->
            space = textPaint.measureText(text)
            totalSize += textPaint.measureText(text)
        }
        totalSize += space
        return totalSize
    }

    // 画中心点
    private fun drawCenter(pathMeasure: PathMeasure, totalLength: Float, canvas: Canvas) {
        val position = FloatArray(2)
        val midpointDistance = totalLength / 2f
        pathMeasure.getPosTan(midpointDistance, position, null)

        val x = position[0]
        val y = position[1]
//        Log.e("BezierCurveView", "drawCenter x: $x , y: $y", )
        val radius = 10f
        canvas.drawCircle(x, y, radius, paint)
    }

    private fun Int.toPx(): Int {
        val scale = resources.displayMetrics.density
        return (this * scale + 0.5f).toInt()
    }
}