package com.example.lifecyclesdemo.viewmodel

import androidx.lifecycle.*
import com.example.lifecyclesdemo.model.LoginModel

class LoginViewModel : ViewModel(), DefaultLifecycleObserver {

    // Model，数据源，获取数据的具体请求及方法
    private val loginModel  = LoginModel()

    // LiveData，存储数据，数据变更回调观察者进行view刷新
    val loginLiveData = MutableLiveData<Boolean>()

    fun doLogin(name: String, password: String) {
        loginModel.loginRequest()
            .subscribe{
                loginLiveData.value = it
            }
    }

    // 释放资源
    override fun onCleared() {
        loginModel.destroy()
        super.onCleared()
    }

    override fun onCreate(owner: LifecycleOwner) {
        println("onCreate +++++++++++++++++++")
    }

    override fun onStop(owner: LifecycleOwner) {
        println("onStop +++++++++++++++++++")
    }

}