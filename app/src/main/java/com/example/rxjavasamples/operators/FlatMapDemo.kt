package com.example.rxjavasamples.operators

import com.example.rxjavasamples.DebugUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class FlatMapDemo {
    val tag : String = FlatMapDemo::class.java.simpleName

    private lateinit var myRangeObservable : Observable<Student>
    private lateinit var myRangeObserver : DisposableObserver<Student>

    private val compositDisposable = CompositeDisposable()

    fun flatMapData(){
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
                .flatMap { (name) ->
                    val student1 = Student()
                    student1.name = "New name " + name!!
                    val student2 = Student()
                    student2.name = "New name2 $name"

                    Observable.just(student1, student2)
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