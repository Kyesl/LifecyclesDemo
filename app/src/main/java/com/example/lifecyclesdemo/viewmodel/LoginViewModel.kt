package com.example.lifecyclesdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lifecyclesdemo.model.LoginModel

class LoginViewModel : ViewModel() {

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

}