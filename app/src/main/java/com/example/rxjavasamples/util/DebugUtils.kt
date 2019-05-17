package com.example.rxjavasamples.util

import android.util.Log

object DebugUtils {

    fun showLog(TAG : String, message : String){
        Log.e(TAG,message)
    }
}