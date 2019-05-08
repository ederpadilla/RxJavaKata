package com.example.rxjavasamples

import android.util.Log

object DebugUtils {

    fun showLog(TAG : String, message : String){
        Log.e(TAG,message)
    }
}