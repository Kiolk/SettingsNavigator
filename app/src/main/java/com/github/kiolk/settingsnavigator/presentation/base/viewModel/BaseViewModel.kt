package com.github.kiolk.settingsnavigator.presentation.base.viewModel

import android.app.AppOpsManager
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel

abstract class BaseViewModel(private val context: Context) : ViewModel() {

    protected fun checkPermission(): Boolean {
        try {
            val packagerManger = context.packageManager
            val appInfo = packagerManger.getApplicationInfo(context.packageName, 0)
            val appOpsManager =
                context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val mode = appOpsManager.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                appInfo.uid,
                appInfo.packageName
            )
            return mode == AppOpsManager.MODE_ALLOWED
        } catch (exception: Exception) {
            Log.d("MyLogs", "checkPermision: ")
        }

        return false
    }
}