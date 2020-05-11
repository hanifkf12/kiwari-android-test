package com.hanifkf12.kiwari_androidtest.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hanifkf12.kiwari_androidtest.R
import com.hanifkf12.kiwari_androidtest.dummy.DummyUser
import com.hanifkf12.kiwari_androidtest.model.Login
import com.hanifkf12.kiwari_androidtest.ui.HomeActivity
import com.hanifkf12.kiwari_androidtest.util.PreferenceHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferenceHelper = PreferenceHelper(this)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val data = DummyUser.getUsers().filter {
            it.email.contains("jarjit@mail.com")
        }.toList()

        viewModel.status.observe(this, Observer {
            Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
        })
        viewModel.user.observe(this, Observer {
            if (it!=null){
                preferenceHelper.username = it.username
                preferenceHelper.isLogin = true
                navigateToHome()
            }
        })

        btn_login.setOnClickListener {
            val email = tv_email.text.toString()
            val password = tv_password.text.toString()
            when {
                email.isEmpty() -> {
                    Toast.makeText(this,"Please Fill Email First",Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(this,"Please Fill Password First",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val dataLogin = Login(email,password)
                    viewModel.loginUser(dataLogin)
                }
            }

        }
    }

    private fun navigateToHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (preferenceHelper.isLogin){
            navigateToHome()
        }
    }
}
