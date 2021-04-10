package com.rnd.app.common.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.rnd.app.R
import com.rnd.app.common.DEFAULT_PRIMARY_COLOR
import com.rnd.app.common.DEFAULT_RADIUS


open class HorizontalProgressBar : View {

    private var shape: RectF = RectF()

    private val progressPaint = Paint()
        .apply {
            isAntiAlias = true
            style = Paint.Style.FILL
        }

    private var primaryProgressStartValue: Float = 0.0f
    private var primaryProgressEndValue: Float = 0.0f
    private var durationValue: Float = 1.0f

    private var primaryProgress: Int = DEFAULT_PRIMARY_COLOR
    private var radius: Float = DEFAULT_RADIUS

    constructor(context: Context) : super(context, null) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            context.theme.obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar, 0, 0)
                .apply {
                    try {
                        primaryProgress = getColor(
                            R.styleable.HorizontalProgressBar_primaryProgress,
                            DEFAULT_PRIMARY_COLOR
                        )
                        radius =
                            getDimension(R.styleable.HorizontalProgressBar_radius, DEFAULT_RADIUS)
                    } finally {
                        recycle()
                    }
                }
        }
    }

    fun setPrimaryProgress(startValue: Float = 0.0f, endValue: Float) {
        if (startValue > endValue || endValue > durationValue) return

        primaryProgressStartValue = startValue
        primaryProgressEndValue = endValue

        invalidate()
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        initShape()
        drawPrimaryProgress(canvas, shape)
    }

    private fun initShape() {
        shape.apply {
            left = 0.0f
            top = 0.0f
            right = width.toFloat()
            bottom = height.toFloat()
        }
    }

    private fun drawPrimaryProgress(canvas: Canvas, rect: RectF) {
        progressPaint.reset()
        rect.left = normalizedToScreen(primaryProgressStartValue)
        rect.right = normalizedToScreen(primaryProgressEndValue)
        progressPaint.color = primaryProgress
        canvas.drawRoundRect(rect, radius, radius, progressPaint)
    }

    private fun normalizedToScreen(normalizedCoordinates: Float): Float = normalizedCoordinates * width

}