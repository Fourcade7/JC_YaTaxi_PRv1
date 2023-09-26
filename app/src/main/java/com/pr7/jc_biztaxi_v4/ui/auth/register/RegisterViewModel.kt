package com.pr7.jc_biztaxi_v4.ui.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.jc_biztaxi_v4.data.model.register.RegisterUser
import com.pr7.jc_biztaxi_v4.data.model.register.RegisterUserResponse
import com.pr7.jc_biztaxi_v4.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class RegisterViewModel constructor():ViewModel() {


    val api=RetrofitInstance.api
    val mlivedataRegisterResponse=MutableLiveData<Result<RegisterUserResponse>>()
    val iscompletedregister=MutableLiveData<Boolean>(false)


    fun registerUser(phone:String)=viewModelScope.launch {
        iscompletedregister.postValue(true)
        try {
            val response=api.registerUser(RegisterUser(phone = phone))
            if (response.isSuccessful){
                mlivedataRegisterResponse.postValue(Result.success(response.body()!!))
                iscompletedregister.postValue(false)

            }else{
                mlivedataRegisterResponse.postValue(Result.failure(Exception(response.errorBody()?.string())))
                iscompletedregister.postValue(false)
            }
        }catch (e:Exception){
            iscompletedregister.postValue(false)
        }

    }

}