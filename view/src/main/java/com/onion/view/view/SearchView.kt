package com.onion.view.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.onion.view.SizeUtils

/**
 * Copyright (C), 2019-2019, 里德软件
 * FileName: SearchView
 * Author: OnionMac by 张琦
 * Date: 2019/4/28 2:38 PM
 * Description:
 */
class SearchView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mWidth: Int = 0
    private var mStart: Int = 0
    private var mHeight: Int = 0

    private var mItemHeight = 0

    private lateinit var moveListener: MoveListener

    fun setMoveListener(moveListener: MoveListener){
       this.moveListener = moveListener
    }

    private var mStrs: ArrayList<String> = arrayListOf(
        "A", "B", "C", "D", "E", "F", "G",
        "H", "I", "J", "K", "L", "M", "N",
        "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z"
    )

    init {
        mPaint.color = Color.BLACK
        mPaint.textSize = SizeUtils.dp2px(10F).toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = width
        mHeight = height

        mItemHeight = mHeight / 26
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)


        mStrs.forEachIndexed { index, s ->
            run {
                var start: Float = mPaint.measureText(s)
                start = (mWidth - start) / 2
                canvas?.drawText(s, start, mItemHeight * (index + 1).toFloat(), mPaint)
            }
        }

    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        event?.let {

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val y = event.y

                    val index = computer(y)
                    if(index >= mStrs.size){
                        return true
                    }
                    moveListener.onMove(index,mStrs[index])
                }

                MotionEvent.ACTION_MOVE -> {
                    val y = event.y

                    val index = computer(y)
                    if(index >= mStrs.size){
                        return true
                    }

//                    if(index <= 0){
//                        return true
//                    }

                    moveListener.onMove(index,mStrs[index])
                }
            }
        }

        return true
    }

    private fun computer(y: Float):Int {

        if(y < 0){
            return 0
        }

        return (y / mItemHeight).toInt()
    }

    public interface MoveListener{
        fun onMove(index: Int, s: String)
    }
}