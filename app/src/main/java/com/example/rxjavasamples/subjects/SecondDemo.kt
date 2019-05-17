package com.example.rxjavasamples.subjects

import com.example.rxjavasamples.util.DebugUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.AsyncSubject

class SecondDemo {

    private val tag : String = SecondDemo::class.java.simpleName

    fun asyncSubjectDemo2(){
        val asyncSubject : AsyncSubject<String> = AsyncSubject.create()
        asyncSubject.subscribe(getFirstObserver())
        asyncSubject.onNext("Java")
        asyncSubject.onNext("Kotlin")
        asyncSubject.onNext("Xml")
        asyncSubject.subscribe(getSecondObserver())
        asyncSubject.onNext("JSON")
        asyncSubject.onComplete()
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