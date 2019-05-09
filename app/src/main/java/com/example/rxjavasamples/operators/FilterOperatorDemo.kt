package com.example.rxjavasamples.operators

import com.example.rxjavasamples.DebugUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FilterOperatorDemo {

    private val tag : String = FilterOperatorDemo::class.java.simpleName

    fun filterData(){
        val observable = Observable.range(1, 20)
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { integer -> integer % 3 == 0 }
            .subscribe(object : Observer<Int> {
                override fun onNext(int: Int) {
                    DebugUtils.showLog(tag,"onNext $int")
                }

                override fun onSubscribe(d: Disposable) {
                    DebugUtils.showLog(tag,"onSubscribe ")
                }

                override fun onError(e: Throwable) {
                    DebugUtils.showLog(tag,"onError ")
                }

                override fun onComplete() {
                    DebugUtils.showLog(tag,"onComplete ")
                }
            })
    }
}