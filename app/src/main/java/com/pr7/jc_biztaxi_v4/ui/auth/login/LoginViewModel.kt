package com.pr7.jc_biztaxi_v4.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.jc_biztaxi_v4.data.model.login.LoginUser
import com.pr7.jc_biztaxi_v4.data.model.login.LoginUserResponse
import com.pr7.jc_biztaxi_v4.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class LoginViewModel constructor():ViewModel() {

    val api=RetrofitInstance.api
    val mlivedataLoginResponse=MutableLiveData<Result<LoginUserResponse>>()
    val iscompletedlogin=MutableLiveData<Boolean>(false)



    fun login(phone:String)=viewModelScope.launch {
        iscompletedlogin.postValue(true)
        try {
            val response=api.loginUser(LoginUser(phone=phone))
            if (response.isSuccessful){
                mlivedataLoginResponse.postValue(Result.success(response.body()!!))

            }else{
                mlivedataLoginResponse.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompletedlogin.postValue(false)

        }catch (e:Exception){
            iscompletedlogin.postValue(false)

        }

    }

}