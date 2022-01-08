package com.shpp.ssierykh.assignment1.utils

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
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
import com.shpp.ssierykh.assignment1.utils.extensions.convertDpToPixels
import com.shpp.ssierykh.assignment1.utils.extensions.convertSpToPixels
import com.shpp.ssierykh.myapplication.extentions.dpToPx
import java.sql.Array
import java.util.*
import kotlin.Array as Arrays


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
    private var textColorsGoogle = "GOOGLE"
    //private lateinit var textColorsGoogle: String
    private var textColorsGoogleArray: CharArray
    private var textColorLength = 0
    private var cornerRadius = DEFAULT_CORNER_RADIUS
    private var marginGoogleX = DEFAULT_MARGIN_X_GOOGLE
    private var marginGoogleY = DEFAULT_MARGIN_Y_GOOGLE
    private var rotationG = DEFAULT_TURN_G


    private var startLaterX = 0F
    private var startLaterY = 0F
    private var longBetweenLater = 0F
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


    init {
        paint.isAntiAlias = true
        Log.e("CustomView", "Init")////////////////////////////////////////////
        setupAttributes(attrs)
        textColorsGoogleArray = textColorsGoogle.toCharArray()
        textColorLength = textColorsGoogle.length
        longBetweenLater = textSizeGoogle * indentFromGOOGLEAttitudeToTextSize
        innerRadiusLaterG = textSizeGoogle * attitudeToTextSizeForInnerRadius
        outerRadiusLaterG = textSizeGoogle * attitudeToTextSizeForOuterRadius
        //setOnClickListener { click() }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val initSizeX = resolveDefaultSizeWidth(widthMeasureSpec)
        val initSizeY = resolveDefaultSizeHeight(heightMeasureSpec)
        setMeasuredDimension(initSizeX, initSizeY)
        Log.e("CustomView", "onMeasure")//////////////////////////////////////
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.e("CustomView", "onSizeChanged")////////////////////////////////////////////
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
       // textColorsGoogle = "GOOGLE"
        /*  textColorsGoogle = typedArray.getString(
              R.styleable.GoogleCustomView_textColorsGoogle
          ) as String
  */
        colorBackgroundGoogle = typedArray.getColor(
            R.styleable.GoogleCustomView_colorBackgroundGoogle, colorBackgroundGoogle
        )

        textSizeGoogle = typedArray.getDimension(
            R.styleable.GoogleCustomView_textSizeGoogle.convertSpToPixels(context),
            DEFAULT_TEXT_SIZE.toFloat()
        ).toInt()
        Log.e("CustomView", "setupAttributes $textSizeGoogle")////////////////////////////////////////////

        cornerRadius = typedArray.getDimension(
            R.styleable.GoogleCustomView_cornerBackground.convertDpToPixels(context),
            DEFAULT_CORNER_RADIUS
        )
        marginGoogleX = typedArray.getDimension(
            R.styleable.GoogleCustomView_marginGoogleX.convertDpToPixels(context),
            DEFAULT_MARGIN_X_GOOGLE
        )
        marginGoogleY = typedArray.getDimension(
            R.styleable.GoogleCustomView_marginGoogleY.convertDpToPixels(context),
            DEFAULT_MARGIN_X_GOOGLE
        )
        rotationG = typedArray.getDimension(
            R.styleable.GoogleCustomView_rotationG,
            DEFAULT_TURN_G.toFloat()
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
            rotationG.toFloat(),
            startLaterX, startLaterY
        )
        drawStartG(canvas)
        arcLetterG(canvas, -12F, 75F, colorBlueGoogle)
        arcLetterG(canvas, 60F, 95F, colorGreenGoogle)
        arcLetterG(canvas, 155F, 45F, colorYellowGoogle)
        arcLetterG(canvas, 200F, 110F, colorRedGoogle)
        canvas.rotate(
            -rotationG.toFloat(),
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


    /*  private fun click(): Boolean {
          textSizeGoogle += 10
          requestLayout()
        *//*  val va: ValueAnimator = ValueAnimator.ofInt(0, 360).apply {
            duration = 1000
            interpolator = LinearInterpolator()
        }
        va.addUpdateListener {
            rotationG = it.animatedValue as Int
            Log.e("CustomView", "Animation $rotationG")///////////////////////
            requestLayout()
        }

        va.start()*//*

        return true
    }*/


}