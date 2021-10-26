package com.mgx.mathwallet5lib

import android.content.Context
import com.mgx.mathwallet5lib.bean.SimpleWallet

interface MathWallet5Api {

    fun <T> requestAction(
        context: Context,
        action: SimpleWallet<T>,
        callBack: MathWallet5CallBack
    )
}