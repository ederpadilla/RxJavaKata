package com.example.rxjavasamples.operators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjavasamples.DebugUtils
import com.example.rxjavasamples.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class OperatorsActivity : AppCompatActivity() {

    final val tag: String = OperatorsActivity::class.java.simpleName
    private val greeting = "Hello from Rx Java"
    private lateinit var myObservable : Observable<String>
    private lateinit var myDisposableObserver : DisposableObserver<String>

    private val compositDisposable = CompositeDisposable()


    private lateinit var myArrayObservable : Observable<Array<String>>
    private lateinit var myDisposableArrayObserver : DisposableObserver<Array<String>>
    private val arrayStrings = arrayOf("Hello A", "Hello B", "Hello C")

    private lateinit var myMultipleObservable : Observable<String>
    private lateinit var myMultipleDisposableObserver : DisposableObserver<String>
    private val multipleStrings = arrayOf("Hello A", "Hello B", "Hello C")

    /**
     * From Array
     * */
    private lateinit var myFromArrayObservable : Observable<Array<String>>
    private lateinit var myDisposableFromArrayObserver : DisposableObserver<Array<String>>
    private val fromArrayStrings = arrayOf("Hello A", "Hello B", "Hello C")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators)
        //simpleObservale()
        //arrayObservable()
        //multipleStringsObservable()
        //fromArraySample()
        val rangeDemo = RangeDemo()
        rangeDemo.getData()
    }

    private fun fromArraySample() {
        myFromArrayObservable = Observable.fromArray(fromArrayStrings)
        compositDisposable.add(
            myFromArrayObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getMyFromArrayObservable())
        )
    }

    private fun getMyFromArrayObservable(): DisposableObserver<Array<String>> {
        myDisposableFromArrayObserver = object : DisposableObserver<Array<String>>() {
            override fun onNext(stringMessage : Array<String>) {
                for (message in stringMessage)
                    DebugUtils.showLog(tag,"onNext $message")
            }

            override fun onComplete() {
                DebugUtils.showLog(tag,"onComplete")
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"onComplete")
            }

        }
        return myDisposableFromArrayObserver
    }

    private fun multipleStringsObservable() {
        myMultipleObservable = Observable.just("Hello A", "Hello B", "Hello C")
        compositDisposable.add(
            myMultipleObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserverString())
        )
    }

    private fun arrayObservable() {
        myArrayObservable = Observable.just(arrayStrings)
        compositDisposable.add(
            myArrayObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(getObserverArray())
        )
    }

    private fun simpleObservale() {
        myObservable = Observable.just(greeting)
        compositDisposable.add(
            myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<String> {
        myDisposableObserver = object : DisposableObserver<String>() {
            override fun onComplete() {
                DebugUtils.showLog(tag,"onComplete")
            }

            override fun onNext(message: String) {
                DebugUtils.showLog(tag,"onNext $message")
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"onComplete")
            }

        }
        return myDisposableObserver
    }

    private fun getObserverArray(): DisposableObserver<Array<String>> {
        myDisposableArrayObserver = object : DisposableObserver<Array<String>>() {
            override fun onNext(stringMessage : Array<String>) {
                for (message in stringMessage)
                    DebugUtils.showLog(tag,"onNext $message")
            }

            override fun onComplete() {
                DebugUtils.showLog(tag,"onComplete")
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"onComplete")
            }

        }
        return myDisposableArrayObserver
    }

    private fun getObserverString(): DisposableObserver<String> {
        myMultipleDisposableObserver = object : DisposableObserver<String>() {
            override fun onNext(stringMessage : String) {
                    DebugUtils.showLog(tag,"onNext $stringMessage")
            }

            override fun onComplete() {
                DebugUtils.showLog(tag,"onComplete")
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"onComplete")
            }

        }
        return myMultipleDisposableObserver
    }

}
