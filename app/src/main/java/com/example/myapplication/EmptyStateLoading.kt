package com.example.myapplication

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.annotation.RequiresApi

class EmptyStateLoading(context: Context, attrSet: AttributeSet) : View(context, attrSet) {

    private val paint1 = Paint().apply {
        color = Color.GRAY
    }
    private val paint2 = Paint().apply {
        color = Color.BLACK
    }
    private val paint3 = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        style = Paint.Style.FILL
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private var positionX = 0f
    private var size: Float = 0f
    private val accelerateInterpolator = AccelerateInterpolator()
    private val radius = 30f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = measuredWidth.toFloat() / 4

        ValueAnimator.ofFloat(-size, measuredWidth.toFloat()).apply {
            duration = 1000
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            interpolator = accelerateInterpolator
            addUpdateListener {
                positionX = it.animatedValue as Float
                invalidate()
            }
        }.start()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint1)

        canvas.drawRect(positionX, -size, size + positionX, height.toFloat(), paint2)

//      canvas.drawRoundRect(0f, 0f, width.toFloat(), height.toFloat(), radius, radius, paint3)
    }

}
