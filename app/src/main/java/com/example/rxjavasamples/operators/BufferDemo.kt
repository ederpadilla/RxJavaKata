package com.example.rxjavasamples.operators

import com.example.rxjavasamples.util.DebugUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BufferDemo {


    private val tag : String = BufferDemo::class.java.simpleName

    fun bufferData(){
        val observable = Observable.range(1, 20)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(3)
            .subscribe(object : Observer<List<Int>> {
                override fun onSubscribe(d: Disposable) {
                    DebugUtils.showLog(tag,"onSubscribe")
                }

                override fun onNext(integers: List<Int>) {
                    DebugUtils.showLog(tag,"onNext ")
                    for (num in integers)
                    DebugUtils.showLog(tag,"num value is $num ")

                }

                override fun onError(e: Throwable) {
                    DebugUtils.showLog(tag,"onError")

                }

                override fun onComplete() {
                    DebugUtils.showLog(tag,"onComplete")

                }
            })
    }

}