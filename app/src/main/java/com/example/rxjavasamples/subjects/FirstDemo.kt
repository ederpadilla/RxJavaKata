package com.example.rxjavasamples.subjects

import com.example.rxjavasamples.util.DebugUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject

class FirstDemo {


    private val tag : String = FirstDemo::class.java.simpleName

    fun asyncSubjectDemo1(){
        val observable : Observable<String> =
            Observable.just("Java","Kotlin","XML","Json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        val asyncSubject : AsyncSubject<String> = AsyncSubject.create()
        observable.subscribe(asyncSubject)
        asyncSubject.subscribe(getFirstObserver())
        asyncSubject.subscribe(getSecondObserver())
        asyncSubject.subscribe(getThirdObserver())
    }

    private fun getFirstObserver() : Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                DebugUtils.showLog(tag,"First observer onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                DebugUtils.showLog(tag,"First observer onSubscribe")

            }

            override fun onNext(t: String) {
                DebugUtils.showLog(tag,"First observer onNext $t")

            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"First observer onError")

            }
        }
    }

    private fun getSecondObserver() : Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                DebugUtils.showLog(tag,"Second observer onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                DebugUtils.showLog(tag,"Second observer onSubscribe")

            }

            override fun onNext(t: String) {
                DebugUtils.showLog(tag,"Second observer onNext $t")

            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"Second observer onError")

            }
        }
    }

    private fun getThirdObserver() : Observer<String> {
        return object : Observer<String> {
            override fun onComplete() {
                DebugUtils.showLog(tag,"Third observer onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                DebugUtils.showLog(tag,"Third observer onSubscribe")

            }

            override fun onNext(t: String) {
                DebugUtils.showLog(tag,"Third observer onNext $t")

            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"Third observer onError")

            }
        }
    }


}