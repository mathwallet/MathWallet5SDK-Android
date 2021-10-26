package com.mgx.mathwallet5lib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RouterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = intent.data
        uri?.let { MathWallet5Manager.uriCallBack(it) }
        finish()
    }
}