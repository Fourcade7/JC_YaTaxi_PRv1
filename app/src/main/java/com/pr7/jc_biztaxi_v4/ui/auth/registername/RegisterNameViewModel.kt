package com.pr7.jc_biztaxi_v4.ui.auth.registername

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.jc_biztaxi_v4.data.model.carinfo.CarListResponse
import com.pr7.jc_biztaxi_v4.data.model.changeuserinfo.ChangeUserInfo
import com.pr7.jc_biztaxi_v4.data.model.changeuserinfo.ChangeUserInfoResponse
import com.pr7.jc_biztaxi_v4.data.model.newdriver.DriverCarRegister
import com.pr7.jc_biztaxi_v4.data.model.newdriver.DriverCarRegisterResponse
import com.pr7.jc_biztaxi_v4.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class RegisterNameViewModel constructor():ViewModel() {

    val api= RetrofitInstance.api
    val mlivedataUserInfoch= MutableLiveData<Result<ChangeUserInfoResponse>>()
    val iscompleteduserinfoch= MutableLiveData<Boolean>(false)

    val mlivedataCarList= MutableLiveData<Result<ArrayList<CarListResponse>>>()
    val iscompletedcarlist= MutableLiveData<Boolean>(false)

    val mlivedatacarreg= MutableLiveData<Result<DriverCarRegisterResponse>>()
    val iscompletedcarreg= MutableLiveData<Boolean>(false)


    var firstname= MutableLiveData<String>("")
    var lastname= MutableLiveData<String>("")
    var carid= MutableLiveData<Int>(0)
    var carname= MutableLiveData<String>("")
    var carcolor= MutableLiveData<String>("")
    var carcolorlang= MutableLiveData<String>("")





    fun changeuserinfo(token:String,firstname:String,lastname:String,usertype:String,gender:String)=viewModelScope.launch {
        iscompleteduserinfoch.postValue(true)
        try {
            val response=api.changeUserinfo(token = "Bearer $token", changeUserInfo = ChangeUserInfo(
                first_name = firstname,
                last_name = lastname,
                passport = "negap",
                gender = gender,
                user_type = usertype
            ))
            if (response.isSuccessful){
                mlivedataUserInfoch.postValue(Result.success(response.body()!!))

            }else{
                mlivedataUserInfoch.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompleteduserinfoch.postValue(false)

        }catch (e:Exception){
            iscompleteduserinfoch.postValue(false)

        }

    }

    fun getCarlist(token:String)=viewModelScope.launch {
        iscompletedcarlist.postValue(true)
        try {
            val response=api.getCarlist(token = "Bearer $token")
            if (response.isSuccessful){
                mlivedataCarList.postValue(Result.success(response.body()!!))

            }else{
                mlivedataCarList.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompletedcarlist.postValue(false)

        }catch (e:Exception){
            iscompletedcarlist.postValue(false)

        }

    }


    fun careregister(token:String,carid:Int,carcolor:String,carnumber:String)=viewModelScope.launch {
        iscompletedcarreg.postValue(true)
        try {
            val response=api.driverCarRegister(token = "Bearer $token",  driverCarRegister = DriverCarRegister(
                air_conditioner = true,
                car = carid,
                car_color = carcolor,
                car_number = carnumber,
                fuel_type = "methane_gas"
            ) )
            if (response.isSuccessful){
                mlivedatacarreg.postValue(Result.success(response.body()!!))

            }else{
                mlivedatacarreg.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompletedcarreg.postValue(false)

        }catch (e:Exception){
            iscompletedcarreg.postValue(false)

        }

    }
}