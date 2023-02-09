package com.example.practicemvvm

import android.os.Bundle
import com.example.practicemvvm.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override val layoutId : Int = R.layout.fragment_main

    override fun init() {
        viewDataBinding.handle = "" // 양방향 데이터바인딩 값 초기화

        setUpBtnListenenr()
    }

    private fun setUpBtnListenenr() {
        viewDataBinding.searchBtn.setOnClickListener {
            val fragment = childFragmentManager.findFragmentById(viewDataBinding.userProfileContainer.id) as UserProfileFragment?

            if (fragment == null) {
                val bundle = Bundle().apply {
                    putString("handle", viewDataBinding.handle)
                }
                childFragmentManager
                    .beginTransaction()
                    .replace(viewDataBinding.userProfileContainer.id, UserProfileFragment().apply {
                        arguments = bundle
                    })
                    .commit()
            } else {
                fragment.userDataViewModel.getUserData(viewDataBinding.handle.toString())
            }
        }
    }
}