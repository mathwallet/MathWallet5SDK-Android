package com.mgx.mathwallet5lib

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mgx.mathwallet5lib.bean.SimpleWallet
import com.mgx.mathwallet5lib.util.LogUtil
import java.net.URLEncoder
import java.util.*
import kotlin.collections.HashMap

object MathWallet5Manager : MathWallet5Api {

    private lateinit var mathWalletCallBack: MathWallet5CallBack

    private const val TAG = "MathWallet5Manager"

    override fun <T> requestAction(
        context: Context, action: SimpleWallet<T>, callBack: MathWallet5CallBack
    ) {
        this.mathWalletCallBack = callBack
        val actionJson = Gson().toJson(action)
        requestUri(context, actionJson)
    }

    private fun requestUri(context: Context, json: String) {
        kotlin.runCatching {
            val uriBuilder = StringBuilder()
            uriBuilder.append(MATHWALLET5_PARAM_URL)
            val encode = URLEncoder.encode(json, "utf-8")
            val uriString = uriBuilder.append(encode).toString()
            LogUtil.e(TAG, "uriString->$uriString")
            val uri = Uri.parse(uriString)
            MathWallet5Helper.openMathWallet5(context, uri)
        }.onFailure {
            LogUtil.e(TAG, "UnsupportedEncodingException->${it.localizedMessage}")
        }
    }

    fun uriCallBack(uri: Uri) {
        val scheme = uri.scheme
        if (TextUtils.isEmpty(scheme)) {
            LogUtil.e(TAG, "scheme is null")
            return
        }
        val query = uri.getQueryParameter("response")
        if (!TextUtils.isEmpty(query)) {
            LogUtil.e(TAG, "callBack->$query")
            query?.run {
                if (mathWalletCallBack != null) {
                    val fromJson = Gson().fromJson<HashMap<String,Any>>(
                        this,
                        object : TypeToken<HashMap<String,Any>>() {}.type)
                    mathWalletCallBack.callback(fromJson, uri.toString())
                } else {
                    LogUtil.e(
                        TAG,
                        "callback is null"
                    )
                }
            }
        } else {
            LogUtil.e(TAG, "query is empty")
        }
    }
}