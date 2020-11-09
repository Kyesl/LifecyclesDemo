package com.example.lifecyclesdemo.model

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LoginModel {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun loginRequest(): Observable<Boolean> {
        return Observable.create(ObservableOnSubscribe<Boolean> {
            Thread.sleep(2000)
            it.onNext(true)
            it.onComplete()
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    // 释放资源
    fun destroy() {
        compositeDisposable.dispose()
    }

}