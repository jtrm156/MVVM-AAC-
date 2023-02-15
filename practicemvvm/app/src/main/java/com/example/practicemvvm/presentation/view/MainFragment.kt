package com.example.practicemvvm.presentation.view

import android.os.Bundle
import com.example.practicemvvm.R
import com.example.practicemvvm.config.BaseFragment
import com.example.practicemvvm.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun init() {
        binding.handle = "" // 양방향 데이터바인딩 값 초기화

        setUpBtnListenenr()
    }

    private fun setUpBtnListenenr() {
        binding.searchBtn.setOnClickListener {
            val fragment = childFragmentManager.findFragmentById(binding.userProfileContainer.id) as UserProfileFragment?

            if (fragment == null) {
                val bundle = Bundle().apply {
                    putString("handle", binding.handle)
                }
                childFragmentManager
                    .beginTransaction()
                    .replace(binding.userProfileContainer.id, UserProfileFragment().apply {
                        arguments = bundle
                    })
                    .commit()
            } else {
                fragment.userDataViewModel.getUserData(binding.handle.toString())
            }
        }
    }
}