package com.example.rxjavasamples.operators

import com.example.rxjavasamples.util.DebugUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SkipLastDemo {

    private val tag : String = SkipLastDemo::class.java.simpleName

    fun skipLastData(){
        val observableNumbers =
            Observable.just(1,2,3,7,5,3,5,5,4,4)
        observableNumbers.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .skipLast(6)
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(number: Int) {
                    DebugUtils.showLog(tag,"On next $number")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                }
            })
    }


}