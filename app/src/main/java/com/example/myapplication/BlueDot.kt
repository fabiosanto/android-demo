package com.example.myapplication

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat

class BlueDot(context: Context, attr: AttributeSet) : View(context, attr) {

    private val radiusMin = 60f // px
    private val radiusMax = 400f // px

    private var dotRadius = 20f // px
    private var circleRadius = radiusMin // px

    private val bluePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
        style = Paint.Style.FILL
    }

    private val blue2Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ResourcesCompat.getColor(context.resources, R.color.colorPrimary2, null)
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cx = width / 2
        val cy = height / 2

        canvas.drawCircle(cx.toFloat(), cy.toFloat(), dotRadius, bluePaint)

        canvas.drawCircle(cx.toFloat(), cy.toFloat(), circleRadius, blue2Paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> moveDot(event)
            MotionEvent.ACTION_DOWN -> animateDot()
        }
        return true
    }

    private fun moveDot(event: MotionEvent) {
        Log.i("moveDot", "value is ${event.rawX} ${event.rawY}")
        x = event.rawX
        y = event.rawY
        invalidate()
    }

    private fun animateDot() {
        val toRadius = if (circleRadius == radiusMin) radiusMax else radiusMin

        ValueAnimator.ofFloat(circleRadius, toRadius).apply {
            addUpdateListener {
                circleRadius = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }
}