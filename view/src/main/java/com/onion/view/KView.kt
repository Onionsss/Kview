package com.onion.view

import android.app.Application
import com.onion.view.KView.app

object KView {
    @JvmStatic
    var app: Application? = null

    @JvmStatic
    fun init(app: Application){
        this.app = app
    }


}

fun getApp(): Application {
    return app!!
}