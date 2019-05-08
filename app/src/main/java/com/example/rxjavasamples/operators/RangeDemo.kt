package com.example.rxjavasamples.operators

import com.example.rxjavasamples.DebugUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class RangeDemo {

    val tag : String = RangeDemo::class.java.simpleName

    private lateinit var myRangeObservable : Observable<Int>
    private lateinit var myRangeObserver : DisposableObserver<Int>
    private val nums = arrayOf(1,2,3,4,5)

    private val compositDisposable = CompositeDisposable()

    fun getData(){
        myRangeObservable = Observable.range(1,20)
        compositDisposable.add(
            myRangeObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getMyRangeArrayObservable())
        )
    }

    private fun getMyRangeArrayObservable(): DisposableObserver<Int> {
        myRangeObserver = object : DisposableObserver<Int>() {
            override fun onNext(number : Int) {
                    DebugUtils.showLog(tag,"onNext $number")
            }

            override fun onComplete() {
                DebugUtils.showLog(tag,"onComplete")
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"onComplete")
            }

        }
        return myRangeObserver
    }

}