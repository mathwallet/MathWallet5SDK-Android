package com.mgx.mathwallet5lib.util

import android.util.Log

object LogUtil {
    var isDebug = false
    fun d(tag: String?, log: String?) {
        if (isDebug) {
            Log.d(tag, log!!)
        }
    }

    fun e(tag: String?, log: String?) {
        if (isDebug) {
            Log.e(tag, log!!)
        }
    }

    fun i(tag: String?, log: String?) {
        if (isDebug) {
            Log.i(tag, log!!)
        }
    }
}