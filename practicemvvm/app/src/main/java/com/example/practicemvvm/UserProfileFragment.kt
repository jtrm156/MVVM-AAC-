package com.example.practicemvvm

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.practicemvvm.databinding.FragmentUserProfileBinding

class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {
    override val layoutId: Int = R.layout.fragment_user_profile

    lateinit var viewModelFactory : APIViewModelFactory
    lateinit var userDataViewModel : UserDataViewModel

    override fun init() {
        initViewModel()
        setUpObserver()
        getUserData()
    }

    private fun initViewModel() {
        viewModelFactory = APIViewModelFactory(SolvedAcAPIRepository())
        userDataViewModel = ViewModelProvider(requireActivity(), viewModelFactory)
            .get(UserDataViewModel::class.java)
    }

    private fun setUpObserver() {
        userDataViewModel.getUserDataRepositories.observe(viewLifecycleOwner) {
            data ->
            viewDataBinding.model = data

            if(data.code != 200) {
                Toast.makeText(requireContext(), "ID에 해당하는 유저가 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUserData() {
        val handle = arguments?.getString("handle")
        userDataViewModel.getUserData(handle.toString())
    }
}