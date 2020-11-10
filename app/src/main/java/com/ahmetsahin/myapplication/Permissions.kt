package com.ahmetsahin.myapplication

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissions {

    fun checkPermission(activity: Activity, permission: String?): Boolean {
        val check = ContextCompat.checkSelfPermission(activity, permission!!)
        return check == PackageManager.PERMISSION_GRANTED
    }

    fun isExternalStorageWritable(): Boolean {
        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    }

    fun requestPermission(context: Activity, permission: String?) {
        val arr = arrayOf(permission)
        ActivityCompat.requestPermissions(context, arr, 0)
    }
}