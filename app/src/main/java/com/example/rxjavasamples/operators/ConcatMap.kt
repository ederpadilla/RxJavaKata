package com.example.rxjavasamples.operators

import com.example.rxjavasamples.DebugUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ConcatMap {

    val tag : String = ConcatMap::class.java.simpleName

    private lateinit var myRangeObservable : Observable<Student>
    private lateinit var myRangeObserver : DisposableObserver<Student>

    private val compositDisposable = CompositeDisposable()

    fun concatMapData(){
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
                .concatMap { (name) ->
                    val student1 = Student()
                    student1.name = "Concat name " + name!!
                    val student2 = Student()
                    student2.name = "Concat name2 $name"

                    Observable.just(student1, student2)
                }
                .subscribeWith(getObserver())
        )
    }


    private fun getObserver(): DisposableObserver<Student> {
        myRangeObserver = object : DisposableObserver<Student>() {
            override fun onNext(student : Student) {
                DebugUtils.showLog(tag,"onNext ${student.name}")
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