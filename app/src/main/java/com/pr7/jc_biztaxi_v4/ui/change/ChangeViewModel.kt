package com.pr7.jc_biztaxi_v4.ui.change

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.jc_biztaxi_v4.data.model.changeusertype.ChangeUserType
import com.pr7.jc_biztaxi_v4.data.model.changeusertype.ChangeUserTypeResponse
import com.pr7.jc_biztaxi_v4.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class ChangeViewModel constructor(): ViewModel() {


    val api= RetrofitInstance.api
    val mlivedataChangeUsertype= MutableLiveData<Result<ChangeUserTypeResponse>>()
    val iscompletedchangetype= MutableLiveData<Boolean>(false)


    fun changeUsertype(token:String,usertype:String)=viewModelScope.launch {
        iscompletedchangetype.postValue(true)
        try {
            val response=api.changeUsertype(token="Bearer $token",ChangeUserType(user_type =usertype))
            if (response.isSuccessful){
                mlivedataChangeUsertype.postValue(Result.success(response.body()!!))
                iscompletedchangetype.postValue(false)

            }else{
                mlivedataChangeUsertype.postValue(Result.failure(Exception(response.errorBody()?.string())))
                iscompletedchangetype.postValue(false)
            }
        }catch (e:Exception){
            iscompletedchangetype.postValue(false)
        }

    }

}