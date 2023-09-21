package com.pr7.jc_yataxi_prv1.ui.auth.otp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.jc_yataxi_prv1.data.model.login.LoginUser
import com.pr7.jc_yataxi_prv1.data.model.login.LoginUserResponse
import com.pr7.jc_yataxi_prv1.data.model.otp.OTPUser
import com.pr7.jc_yataxi_prv1.data.model.otp.OTPUserResponse
import com.pr7.jc_yataxi_prv1.data.model.userinfo.UserInfoResponse
import com.pr7.jc_yataxi_prv1.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class OTPViewModel constructor():ViewModel() {


    val api= RetrofitInstance.api
    val mlivedataOtpResponse= MutableLiveData<Result<OTPUserResponse>>()
    val iscompletedotp= MutableLiveData<Boolean>(false)

    val mlivedataUserInfo= MutableLiveData<Result<UserInfoResponse>>()
    val iscompleteduserinfo= MutableLiveData<Boolean>(false)



    fun otpverify(code:String)=viewModelScope.launch {
        iscompletedotp.postValue(true)
        try {
            val response=api.otpVerify(OTPUser(code = code))
            if (response.isSuccessful){
                mlivedataOtpResponse.postValue(Result.success(response.body()!!))

            }else{
                mlivedataOtpResponse.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompletedotp.postValue(false)

        }catch (e:Exception){
            iscompletedotp.postValue(false)

        }

    }

    fun getuserinfo(token:String)=viewModelScope.launch {
        iscompleteduserinfo.postValue(true)
        try {
            val response=api.getUserinfo(token = "Bearer $token")
            if (response.isSuccessful){
                mlivedataUserInfo.postValue(Result.success(response.body()!!))

            }else{
                mlivedataUserInfo.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompleteduserinfo.postValue(false)

        }catch (e:Exception){
            iscompleteduserinfo.postValue(false)

        }

    }

}