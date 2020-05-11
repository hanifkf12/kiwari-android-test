package com.hanifkf12.kiwari_androidtest.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.hanifkf12.kiwari_androidtest.R
import com.hanifkf12.kiwari_androidtest.ui.login.MainActivity
import com.hanifkf12.kiwari_androidtest.util.PreferenceHelper
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var dashboardViewModel: ProfileViewModel
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        preferenceHelper = PreferenceHelper(requireActivity())
        dashboardViewModel.showProfile(preferenceHelper.username)
        dashboardViewModel.user.observe(viewLifecycleOwner, Observer {
            Glide.with(this).load(it.avatar).into(iv_profile)
            tv_name.text = it.name
            tv_email.text = it.email
            tv_username.text = it.username
        })

        btn_logout.setOnClickListener {
            preferenceHelper.isLogin = false
            preferenceHelper.username = null
            navigateToLogin()
        }
    }

    private fun navigateToLogin(){
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
