package com.onion.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Copyright (C), 2019-2019, 里德软件
 * FileName: MoldDialog
 * Author: OnionMac by 张琦
 * Date: 2019/5/16 3:19 PM
 * Description:values
 */
open class MoldDialog<T: ViewDataBinding>(context: Context,val builder: Builder) : Dialog(context, R.style.capitaldialog) {

    lateinit var mBinding: T

    private var mContext = context
    private var mActivity = context as Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), builder.layoutRes,null,false)

        setContentView(mBinding.root)

        setCanceledOnTouchOutside(true)
        setCancelable(true)
        val windowManager = mActivity.windowManager
        val display = windowManager.defaultDisplay

        val mWindow = window
        if (builder.windowAnim != 0) {
            mWindow?.setWindowAnimations(builder.windowAnim)
        } else {
        }

        val lp = mWindow?.attributes
        //设置居中
        mWindow?.setGravity(builder.gravity)
        lp?.y = SizeUtils.dp2px(builder.moveY.toFloat())
        lp?.alpha = 1.0f
        lp?.width = (display.getWidth() * builder.widthPer).toInt()
        window!!.attributes = lp
    }

    class Builder {
        var layoutRes: Int = 0
        var widthPer: Double = 0.55
        var windowAnim: Int = R.style.`person＿dialog_anim`
        var moveY: Int = 0
        var gravity: Int = Gravity.CENTER

        fun setLayout(layoutRes: Int): Builder {
            this.layoutRes = layoutRes
            return this
        }

        fun setWidthPer(widthPer: Double): Builder {
            this.widthPer = widthPer
            return this
        }

        fun setWindowAnim(windowAnim: Int): Builder {
            this.windowAnim = windowAnim
            return this
        }

        fun setMoveY(moveY: Int): Builder {
            this.moveY = moveY
            return this
        }

        fun setGravity(gravity: Int = Gravity.CENTER): Builder{
            this.gravity = gravity
            return this
        }
    }

}