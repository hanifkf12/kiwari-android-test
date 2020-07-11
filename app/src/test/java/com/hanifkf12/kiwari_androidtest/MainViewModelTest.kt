package com.hanifkf12.kiwari_androidtest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hanifkf12.kiwari_androidtest.dummy.DummyUser
import com.hanifkf12.kiwari_androidtest.model.Login
import com.hanifkf12.kiwari_androidtest.model.User
import com.hanifkf12.kiwari_androidtest.ui.login.LoginViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class MainViewModelTest {
    private lateinit var loginViewModel: LoginViewModel
    @get:Rule
    public val instantTaskExecutorRule : InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var myObserver : Observer<User>
    


    @Before
    @Throws(Exception::class)
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel()
    }

    @Test
    fun getChat(){
        val user = DummyUser.getUsers()[1]
        val dataLogin = Login("mail@mail.com","123456")
//        `when`(loginViewModel.loginUser(dataLogin)).thenReturn(user)
        loginViewModel.user.observeForever(myObserver)
        loginViewModel.loginUser(dataLogin)
        verify(myObserver).onChanged(user)
        Assert.assertNotNull(loginViewModel.user.value)
//        Assert.assertEquals(user, loginViewModel.user.value)
    }


    @Test
    fun test(){
        Assert.assertEquals(4, 2 + 2)

    }
}