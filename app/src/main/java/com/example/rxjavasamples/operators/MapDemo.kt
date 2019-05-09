package com.example.rxjavasamples.operators

import com.example.rxjavasamples.DebugUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MapDemo {

    val tag : String = MapDemo::class.java.simpleName
    private lateinit var myRangeObservable : Observable<Student>
    private lateinit var myRangeObserver : DisposableObserver<Student>


    private val compositDisposable = CompositeDisposable()

    fun mapData(){
        myRangeObservable = Observable.create { emitter ->
            for (student in StudentUtils.getStudents()) {
                emitter.onNext(student)
            }
            emitter.onComplete()
        }
        compositDisposable.add(

            myRangeObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { student ->
                    student.name = student.name!!.toUpperCase()
                    student
                }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<Student> {
        myRangeObserver = object : DisposableObserver<Student>() {
            override fun onNext(student : Student) {
                DebugUtils.showLog(tag,"onNext ${student.name}" +
                        " and ${student.age}")
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