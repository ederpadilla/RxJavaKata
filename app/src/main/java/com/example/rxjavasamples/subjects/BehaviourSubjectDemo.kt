package com.example.rxjavasamples.subjects

import com.example.rxjavasamples.util.DebugUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class BehaviourSubjectDemo {

    private val tag : String = BehaviourSubjectDemo::class.java.simpleName

    fun behaviourSubjectDemo(){
        val observable : Observable<String> =
            Observable.just("Java","Kotlin","XML","Json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        val subject : BehaviorSubject<String> = BehaviorSubject.create()
        observable.subscribe(subject)
        subject.subscribe(getFirstObserver())
        subject.subscribe(getSecondObserver())
        subject.subscribe(getThirdObserver())
    }

    fun behaviourSubjectDemo2(){
        val behaviorSubject : BehaviorSubject<String> = BehaviorSubject.create()
        behaviorSubject.subscribe(getFirstObserver())
        behaviorSubject.onNext("Java")
        behaviorSubject.onNext("Kotlin")
        behaviorSubject.onNext("Xml")
        behaviorSubject.subscribe(getSecondObserver())
        behaviorSubject.onNext("JSON")
        behaviorSubject.onComplete()
        behaviorSubject.subscribe(getThirdObserver())
    }

    fun publishSubjectDemo(){
        val observable : Observable<String> =
            Observable.just("Java","Kotlin","XML","Json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        val subject : PublishSubject<String> = PublishSubject.create()
        observable.subscribe(subject)
        subject.subscribe(getFirstObserver())
        subject.subscribe(getSecondObserver())
        subject.subscribe(getThirdObserver())
    }

    fun publishSubjectDemo2(){
        val publishSubject : PublishSubject<String> = PublishSubject.create()
        publishSubject.subscribe(getFirstObserver())
        publishSubject.onNext("Java")
        publishSubject.onNext("Kotlin")
        publishSubject.onNext("Xml")
        publishSubject.subscribe(getSecondObserver())
        publishSubject.onNext("JSON")
        publishSubject.onComplete()
        publishSubject.subscribe(getThirdObserver())
    }

    fun replaySubjectDemo(){
        val observable : Observable<String> =
            Observable.just("Java","Kotlin","XML","Json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        val subject : ReplaySubject<String> = ReplaySubject.create()
        observable.subscribe(subject)
        subject.subscribe(getFirstObserver())
        subject.subscribe(getSecondObserver())
        subject.subscribe(getThirdObserver())
    }

    fun replaySubjectDemo2(){
        val publishSubject : ReplaySubject<String> = ReplaySubject.create()
        publishSubject.subscribe(getFirstObserver())
        publishSubject.onNext("Java")
        publishSubject.onNext("Kotlin")
        publishSubject.onNext("Xml")
        publishSubject.subscribe(getSecondObserver())
        publishSubject.onNext("JSON")
        publishSubject.onComplete()
        publishSubject.subscribe(getThirdObserver())
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