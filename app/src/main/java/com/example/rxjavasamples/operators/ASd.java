package com.example.rxjavasamples.operators;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

public class ASd {

    private Observable<Student> myRangeObservable ;
    private DisposableObserver<Student> myRangeObserver ;
    private CompositeDisposable compositDisposable = new CompositeDisposable();
    public void flatmap(){
       /* compositDisposable.add(

                myRangeObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(new Function<Student, Observable<Student>>() {
                            @Override
                            public Observable<Student> apply(Student student) throws Exception {
                                Student student1 = new Student();
                                student1.setName("Concat name "+student.getName());
                                Student student2 = new Student();
                                student2.setName("Concat name2 "+student.getName());

                                return Observable.just(student1,student2);
                            }
                        })
                .subscribeWith(getStudentsObserver())
        );*/
        Observable<Integer> observable  = Observable.range(1,20);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(4)
                .subscribe(new Observer<List<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Integer> integers) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
