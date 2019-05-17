package com.example.rxjavasamples.operators

import com.example.rxjavasamples.util.DebugUtils
import com.example.rxjavasamples.operators.StudentUtils.getStudents
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CreateDemo {
    val tag : String = CreateDemo::class.java.simpleName

    private lateinit var myRangeObservable : Observable<Student>
    private lateinit var myRangeObserver : DisposableObserver<Student>

    private val compositDisposable = CompositeDisposable()

    fun getData(){
        myRangeObservable = Observable.create(object : ObservableOnSubscribe<Student>{
            override fun subscribe(emitter: ObservableEmitter<Student>) {
                   for (student in getStudents()){
                       emitter.onNext(student)
                   }
                emitter.onComplete()
            }
        })
        compositDisposable.add(
            myRangeObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getStudentsObserver())
        )
    }

    private fun getStudentsObserver(): DisposableObserver<Student> {
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