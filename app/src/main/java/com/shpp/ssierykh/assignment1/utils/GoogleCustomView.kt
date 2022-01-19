package com.shpp.ssierykh.assignment1.utils

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.shpp.ssierykh.assignment1.R
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_BACKGRAUND_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_BLUE_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_GREEN_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_RED_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_COLOR_YELLOW_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_CORNER_RADIUS
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_MARGIN_X_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_MARGIN_Y_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_SIZE_HEIGHT_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_SIZE_WIDTH_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_TEXT_COLOR_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_TEXT_SIZE
import com.shpp.ssierykh.assignment1.utils.Constants.DEFAULT_TURN_G
import com.shpp.ssierykh.assignment1.utils.Constants.DEGREE_180_ANGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DEGREE_360_ANGLE
import com.shpp.ssierykh.assignment1.utils.Constants.DURATION_ANIMATION_GOOGLE
import com.shpp.ssierykh.assignment1.utils.Constants.MOVE_LONG_BETWEEN_LATER
import com.shpp.ssierykh.assignment1.utils.extensions.convertDpToPixels
import com.shpp.ssierykh.myapplication.extentions.dpToPx
import kotlin.properties.Delegates


class GoogleCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val attitudeToTextSizeForInnerRadius = 0.40F
    private val attitudeToTextSizeForOuterRadius = 0.65F
    private val indentFromGAttitudeToTextSize = 3
    private val indentFromGOOGLEAttitudeToTextSize = 0.65F


    //colors default
    private var colorBackgroundGoogle = Color.parseColor(DEFAULT_COLOR_BACKGRAUND_GOOGLE)
    private var colorBlueGoogle = Color.parseColor(DEFAULT_COLOR_BLUE_GOOGLE)
    private var colorRedGoogle = Color.parseColor(DEFAULT_COLOR_RED_GOOGLE)
    private var colorYellowGoogle = Color.parseColor(DEFAULT_COLOR_YELLOW_GOOGLE)
    private var colorGreenGoogle = Color.parseColor(DEFAULT_COLOR_GREEN_GOOGLE)
    private val colorOrderGoogle = arrayOf(
        colorBlueGoogle, colorRedGoogle, colorYellowGoogle, colorBlueGoogle, colorGreenGoogle,
        colorRedGoogle, colorYellowGoogle, colorBlueGoogle, colorGreenGoogle, colorRedGoogle
    )

    //dimens
    private var textSizeGoogle = 0
    private var textColorsGoogle = "DEFAULT_TEXT_COLOR_GOOGLE"
    private var textColorsGoogleArray: CharArray
    private var textColorLength = 0
    private var cornerRadius = DEFAULT_CORNER_RADIUS
    private var marginGoogleX = DEFAULT_MARGIN_X_GOOGLE
    private var marginGoogleY = DEFAULT_MARGIN_Y_GOOGLE
    private var rotationG = DEFAULT_TURN_G



    private var startLaterX = 0F
    private var startLaterY = 0F
    private var longBetweenLater = 0F
    private var startLongBetweenLater = 0F
    private var shiftGoogleX = 0F
    private var shiftGoogleY = 0F
    private var startLaterYMinusHalfRadius = 0F
    private var startLaterYPlusHalfRadius = 0F
    private var startLaterXMinusOuterRadius = 0F
    private var startLaterYMinusOuterRadius = 0F
    private var startLaterXPlusOuterRadius = 0F
    private var startLaterYPlusOuterRadius = 0F
    private var startLaterXMinusInnerRadius = 0F
    private var startLaterYMinusInnerRadius = 0F
    private var startLaterXPlusInnerRadius = 0F
    private var startLaterYPlusInnerRadius = 0F
    private var innerRadiusLaterG = 0F
    private var outerRadiusLaterG = 0F
    private var durationAnimationGoogle = 0L
    private var moveLongBetweenLater = 0



    init {
        paint.isAntiAlias = true
        if (attrs != null) setupAttributes(attrs)
        textColorsGoogleArray = textColorsGoogle.toCharArray()
        textColorLength = textColorsGoogle.length
        longBetweenLater = textSizeGoogle * indentFromGOOGLEAttitudeToTextSize
        innerRadiusLaterG = textSizeGoogle * attitudeToTextSizeForInnerRadius
        outerRadiusLaterG = textSizeGoogle * attitudeToTextSizeForOuterRadius
        startLongBetweenLater = longBetweenLater
        setOnLongClickListener {
            onAnimationG()
        }

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val initSizeX = resolveDefaultSizeWidth(widthMeasureSpec)
        val initSizeY = resolveDefaultSizeHeight(heightMeasureSpec)
        setMeasuredDimension(initSizeX, initSizeY)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        startLaterY = startDrawingForCenteringHeight()
        startLaterX = startDrawingForCenteringWidth()

        shiftGoogleX = startLaterX + outerRadiusLaterG
        shiftGoogleY = startLaterY + textSizeGoogle / indentFromGAttitudeToTextSize
        startLaterYMinusHalfRadius = startLaterY - ((outerRadiusLaterG - innerRadiusLaterG) / 2)
        startLaterYPlusHalfRadius = startLaterY + ((outerRadiusLaterG - innerRadiusLaterG) / 2)

        startLaterXMinusOuterRadius = startLaterX - outerRadiusLaterG
        startLaterYMinusOuterRadius = startLaterY - outerRadiusLaterG
        startLaterXPlusOuterRadius = startLaterX + outerRadiusLaterG
        startLaterYPlusOuterRadius = startLaterY + outerRadiusLaterG

        startLaterXMinusInnerRadius = startLaterX - innerRadiusLaterG
        startLaterYMinusInnerRadius = startLaterY - innerRadiusLaterG
        startLaterXPlusInnerRadius = startLaterX + innerRadiusLaterG
        startLaterYPlusInnerRadius = startLaterY + innerRadiusLaterG
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas != null) {
            drawBackground(canvas)
        }
        if (canvas != null) {
            drawG(canvas)
        }
        if (canvas != null) {
            drawTextColorGoogle(canvas)
        }


    }

    private fun setupAttributes(attrs: AttributeSet?) {

        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.GoogleCustomView,
            0, 0
        )

        textColorsGoogle = typedArray.getString(
            R.styleable.GoogleCustomView_textColorsGoogle
        ) ?: DEFAULT_TEXT_COLOR_GOOGLE
        colorBackgroundGoogle = typedArray.getColor(
            R.styleable.GoogleCustomView_colorBackgroundGoogle, colorBackgroundGoogle
        )

        textSizeGoogle = typedArray.getDimension(
            R.styleable.GoogleCustomView_textSizeGoogle,
            DEFAULT_TEXT_SIZE
        ).toInt()
        // Log.e("CustomView", "setupAttributes $textSizeGoogle")////////////////////////////////////////////

        cornerRadius = typedArray.getDimension(
            R.styleable.GoogleCustomView_cornerBackground,
            DEFAULT_CORNER_RADIUS
        ).convertDpToPixels(context)
        marginGoogleX = typedArray.getDimension(
            R.styleable.GoogleCustomView_marginGoogleX,
            DEFAULT_MARGIN_X_GOOGLE
        ).convertDpToPixels(context)
        marginGoogleY = typedArray.getDimension(
            R.styleable.GoogleCustomView_marginGoogleY,
            DEFAULT_MARGIN_X_GOOGLE
        ).convertDpToPixels(context)
        rotationG = (typedArray.getDimension(
            R.styleable.GoogleCustomView_rotationG,
            DEFAULT_TURN_G
        ).toDouble() / 2.7).toFloat()
        durationAnimationGoogle = typedArray.getDimension(
            R.styleable.GoogleCustomView_duration_animation,
            DURATION_ANIMATION_GOOGLE.toFloat()
        ).toLong()
        moveLongBetweenLater = typedArray.getDimension(
            R.styleable.GoogleCustomView_move_letter_animation,
            DEFAULT_TURN_G
        ).toInt()

        typedArray.recycle()

    }

    private fun resolveDefaultSizeWidth(spec: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_SIZE_WIDTH_GOOGLE).toInt()
            MeasureSpec.AT_MOST -> (marginGoogleX * 2 + pairRadiusLaterG().second *
                    (textColorLength + 1) + pairRadiusLaterG().first * 2).toInt()
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            else -> MeasureSpec.getSize(spec)
        }
    }

    private fun resolveDefaultSizeHeight(spec: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_SIZE_HEIGHT_GOOGLE).toInt()
            MeasureSpec.AT_MOST -> (marginGoogleY * 2 + pairRadiusLaterG().first * 2 +
                    pairRadiusLaterG().second).toInt()
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(spec)
            else -> MeasureSpec.getSize(spec)
        }
    }

    private fun startDrawingForCenteringWidth(): Float {
        return (measuredWidth - (marginGoogleX * 2 + pairRadiusLaterG().second * (textColorsGoogle.length + 1) +
                pairRadiusLaterG().first * 3).toInt()) / 2 +
                pairRadiusLaterG().second / 2 + pairRadiusLaterG().first + marginGoogleX
    }

    private fun startDrawingForCenteringHeight(): Float {
        return (measuredHeight - (marginGoogleY * 2 + pairRadiusLaterG().first * 2 +
                pairRadiusLaterG().second).toInt()) / 2 +
                pairRadiusLaterG().second / 2 + pairRadiusLaterG().first + marginGoogleY
    }


    private fun pairRadiusLaterG(): Pair<Float, Float> {
        val innerRadiusLaterG = textSizeGoogle * attitudeToTextSizeForInnerRadius
        val outerRadiusLaterG = textSizeGoogle * attitudeToTextSizeForOuterRadius
        return Pair(innerRadiusLaterG, outerRadiusLaterG)
    }

    private fun drawBackground(canvas: Canvas) {
        paint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
            paint.color = colorBackgroundGoogle
        }

        canvas.drawRoundRect(
            0F,
            0F,
            measuredWidth.toFloat(),
            measuredHeight.toFloat(),
            cornerRadius,
            cornerRadius,
            paint
        )
    }

    private fun drawG(canvas: Canvas) {
        canvas.rotate(
            rotationG,
            startLaterX, startLaterY
        )
        drawStartG(canvas)
        arcLetterG(canvas, -12F, 75F, colorBlueGoogle)
        arcLetterG(canvas, 60F, 95F, colorGreenGoogle)
        arcLetterG(canvas, 155F, 45F, colorYellowGoogle)
        arcLetterG(canvas, 200F, 110F, colorRedGoogle)
        canvas.rotate(
            -rotationG,
            startDrawingForCenteringWidth(),
            startDrawingForCenteringHeight()
        )
    }

    private fun drawStartG(canvas: Canvas) {
        paint.apply {
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        paint.color = colorBlueGoogle
        canvas.drawRect(
            startLaterX, startLaterYMinusHalfRadius,
            startLaterXPlusOuterRadius, startLaterYPlusHalfRadius,
            paint
        )
    }

    private fun arcLetterG(canvas: Canvas?, arc_ofSet: Float, arc_sweep: Float, colorArc: Int) {

        val outerRect = RectF(

            (startLaterXMinusOuterRadius),
            (startLaterYMinusOuterRadius),
            (startLaterXPlusOuterRadius),
            (startLaterYPlusOuterRadius)
        )
        val innerRect = RectF(
            (startLaterXMinusInnerRadius),
            (startLaterYMinusInnerRadius),
            (startLaterXPlusInnerRadius),
            (startLaterYPlusInnerRadius)
        )

        val path = Path()
        path.arcTo(outerRect, arc_ofSet, arc_sweep)
        path.arcTo(innerRect, (arc_ofSet + arc_sweep), (-arc_sweep))
        path.close()

        val fill = Paint()
        fill.color = colorArc
        canvas!!.drawPath(path, fill)

        val border = Paint()
        border.style = Paint.Style.STROKE
        border.strokeWidth = 2F
    }


    private fun drawTextColorGoogle(canvas: Canvas) {
        paint.apply {
            textSize = textSizeGoogle.toFloat()
            typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        }

        for (i in 1..textColorLength) {

            paint.color = if (i < 10) {
                colorOrderGoogle[i - 1]
            } else {
                colorOrderGoogle[i - i / 10 * 10]
            }

            canvas.drawText(
                "${textColorsGoogleArray[i - 1]}",
                shiftGoogleX + longBetweenLater * (i), shiftGoogleY, paint
            )
        }
    }

    //Animation letter G
    private fun onAnimationG(): Boolean {

            val startRotation = rotationG
            val startLongBetweenLater = longBetweenLater
            val moveLongBetweenLater = DEGREE_180_ANGLE / MOVE_LONG_BETWEEN_LATER
            val va: ValueAnimator = ValueAnimator.ofFloat(0F, DEGREE_360_ANGLE).apply {
                duration = durationAnimationGoogle
                interpolator = LinearInterpolator()
            }
            va.addUpdateListener {
                val diffAnimation = it.animatedValue as Float
                rotationG = diffAnimation + startRotation

                when {
                    diffAnimation < DEGREE_180_ANGLE -> {
                        longBetweenLater = startLongBetweenLater + diffAnimation / moveLongBetweenLater
                    }
                    diffAnimation >= DEGREE_180_ANGLE -> {
                        longBetweenLater = startLongBetweenLater + (DEGREE_360_ANGLE - diffAnimation ) / moveLongBetweenLater
                    }
                }
                invalidate()
            }
            va.start()

        return true
    }

}


