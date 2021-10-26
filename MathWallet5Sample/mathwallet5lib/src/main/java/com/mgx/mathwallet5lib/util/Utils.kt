package com.mgx.mathwallet5lib.util

import android.content.Context
import android.content.pm.PackageManager.NameNotFoundException

class Utils {

    companion object {
        fun isAppInstall(context: Context, packageName: String): Boolean {
            return try {
                val info = context.packageManager.getApplicationInfo(packageName, 0)
                info != null
            } catch (e: NameNotFoundException) {
                e.printStackTrace()
                false
            }
        }
    }
}