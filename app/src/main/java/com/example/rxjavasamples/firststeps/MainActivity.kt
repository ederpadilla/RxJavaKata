package com.example.rxjavasamples.firststeps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rxjavasamples.R
import com.example.rxjavasamples.util.DebugUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    final val tag: String = MainActivity::class.java.simpleName

    private val greeting = "Hello from Rx Java"

    private lateinit var myObservable : Observable<String>

    private lateinit var myObserver : Observer<String>

    private lateinit var myDisposableObserver : DisposableObserver<String>

    private lateinit var myCompositeDisposable : DisposableObserver<String>

    private val compositDisposable = CompositeDisposable()

    //private lateinit var myDisposable : Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myObservable = Observable.just(greeting)
        //observerMode()
        disposableObserver()
        compositeObserverMode()
        compositDisposable.add(
            myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(myDisposableObserver)
        )
        compositDisposable.add(
            myObservable.subscribeWith(myCompositeDisposable)
        )

    }

    private fun disposableObserver() {
        myDisposableObserver = object : DisposableObserver<String>() {
            override fun onComplete() {
                DebugUtils.showLog(tag,"onComplete")
            }

            override fun onNext(t: String) {
                DebugUtils.showLog(tag,"onNext")
                mTvGreeting.text = t
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"onComplete")
            }

        }

    }

    private fun observerMode() {
        myObserver = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                //myDisposable = d
                DebugUtils.showLog(tag,"onSubscribe")
            }

            override fun onNext(s: String) {
                DebugUtils.showLog(tag,"onNext")
                mTvGreeting.text = s
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"onError")
            }

            override fun onComplete() {
                DebugUtils.showLog(tag,"onComplete")
            }
        }
        myObservable.subscribe(myObserver)
    }

    private fun compositeObserverMode() {
        myCompositeDisposable = object : DisposableObserver<String>() {
            override fun onComplete() {
                DebugUtils.showLog(tag,"onComplete")
            }

            override fun onNext(t: String) {
                DebugUtils.showLog(tag,"onNext")
                Toast.makeText(applicationContext,t,Toast.LENGTH_SHORT).show()
            }

            override fun onError(e: Throwable) {
                DebugUtils.showLog(tag,"onComplete")
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        //myDisposableObserver.dispose()
        //myDisposable.dispose()
        compositDisposable.clear()
    }
}
