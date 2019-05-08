package com.example.rxjavasamples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    final val TAG = MainActivity::class.java.simpleName

    private val greeting = "Hello from Rx Java"

    private lateinit var myObservable : Observable<String>

    private lateinit var myObserver : Observer<String>

    private lateinit var myDisposableObserver : DisposableObserver<String>

    //private lateinit var myDisposable : Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myObservable = Observable.just(greeting)
        myObservable.subscribeOn(Schedulers.io())
        myObservable.observeOn(AndroidSchedulers.mainThread())
        //observerMode()
        disposableObserver()

    }

    private fun disposableObserver() {
        myDisposableObserver = object : DisposableObserver<String>() {
            override fun onComplete() {
                DebugUtils.showLog(TAG,"onComplete")
            }

            override fun onNext(t: String) {
                DebugUtils.showLog(TAG,"onNext")
                mTvGreeting.text = t
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(TAG,"onComplete")
            }

        }
        myObservable.subscribe(myDisposableObserver)
    }

    private fun observerMode() {
        myObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                //myDisposable = d
                DebugUtils.showLog(TAG,"onSubscribe")
            }

            override fun onNext(s: String) {
                DebugUtils.showLog(TAG,"onNext")
                mTvGreeting.text = s
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(TAG,"onError")
            }

            override fun onComplete() {
                DebugUtils.showLog(TAG,"onComplete")
            }
        }
        myObservable.subscribe(myObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        myDisposableObserver.dispose()
        //myDisposable.dispose()
    }
}
