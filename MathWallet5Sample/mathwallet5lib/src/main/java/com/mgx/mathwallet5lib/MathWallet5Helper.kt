package com.mgx.mathwallet5lib

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mgx.mathwallet5lib.util.Utils

class MathWallet5Helper {

    companion object {

        fun openMathWallet5(context: Context, uri: Uri) {
            val isAppInstall = Utils.isAppInstall(context, MATHWALLET5_PACKAGE_NAME)
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    if (isAppInstall) uri else Uri.parse(MATHWALLET5_DOWNLOAD_URL)
                ).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
        }
    }
}