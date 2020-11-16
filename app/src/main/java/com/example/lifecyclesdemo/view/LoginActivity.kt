package com.example.lifecyclesdemo.view

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lifecyclesdemo.R
import com.example.lifecyclesdemo.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var mLoginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 初始化ViewModel
        initViewModel()
        // 初始化LiveData的观察者
        initLiveDataObserver()

        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener{
            login()
        }
    }

    private fun initViewModel () {
        // 通过ViewModelProvider获取对应class的ViewModel
        mLoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // LoginViewModel实现了LifecycleObserver接口，注册为lifecycle的观察者，LoginViewModel即可感知该Activity的生命周期
        lifecycle.addObserver(mLoginViewModel)
    }

    private fun initLiveDataObserver () {
        mLoginViewModel.loginLiveData.observe(this, Observer<Boolean?> {
            Toast.makeText(this, "Login is ${if (it != null && it) "success" else "failed"}", Toast.LENGTH_SHORT)
                .show()
        })
    }


    /**
     * 登陆具体逻辑
     */
    private fun login() {
        mLoginViewModel.doLogin("", "")
    }

}