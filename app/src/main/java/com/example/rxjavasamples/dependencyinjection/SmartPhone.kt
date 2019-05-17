package com.example.rxjavasamples.dependencyinjection

import android.util.Log

class SmartPhone(private val battery: Battery, private val memoryCard: MemoryCard, private val simCard: SIMCard) {


    fun makeACall() {
        Log.d(TAG, " making a call......... ")
    }

    companion object {
        private val TAG = "SmartPhone"
    }
}
