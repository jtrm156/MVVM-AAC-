package com.example.practicemvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ConnectException

class UserDataViewModel(private val repository : SolvedAcAPIRepository) : ViewModel() {
    val getUserDataRepositories = MutableLiveData<SolveAcGetUserDataModel>()

    fun getUserData(handle : String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.getUserData(handle).let {
                    response ->
                    Log.d("api_request_url::", response.raw().request.url.toString())
                    Log.d("get_user_api", response.code().toString() + " " + response.message())
                    if (response.code() == 200) {
                        response.body()?.code = response.code()

                        response.body()?.let {
                            setTierText(it)
                        }
                        Log.e("12345","${response.body()}")
                        getUserDataRepositories.postValue((response.body()))
                    } else {
                        getUserDataRepositories.postValue(SolveAcGetUserDataModel(
                            "",
                            "",
                            mutableListOf(),
                            SolveAcGetUserDataModel.BackgroundData("","",""),
                            "",
                            0,
                            0).apply {
                                code = response.code()
                        })
                    }
                }
            } catch (e: ConnectException) {
                e.printStackTrace()
                Log.d("api_exception", e.toString())
            } catch (e : Exception) {
                e.printStackTrace()
                Log.d("api_exception", e.toString())
            }
        }
    }

    private fun setTierText(model : SolveAcGetUserDataModel) {
        when {
            model.tier < 6 -> {
                model.tierText = "Bronze " + (6 - model.tier)
            }
            model.tier < 11 -> {
                model.tierText = "Silver " + (6 - model.tier)
            }
            model.tier < 16 -> {
                model.tierText = "Gold " + (6 - model.tier)
            }
            model.tier < 21 -> {
                model.tierText = "Platinum " + (6 - model.tier)
            }
            model.tier < 26 -> {
                model.tierText = "Diamond " + (6 - model.tier)
            }
            model.tier < 31 -> {
                model.tierText = "Ruby " + (6 - model.tier)
            }
            model.tier < 36 -> {
                model.tierText = "Master"
            }
        }
    }
}