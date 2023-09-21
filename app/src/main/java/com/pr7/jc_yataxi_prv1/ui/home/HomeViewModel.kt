package com.pr7.jc_yataxi_prv1.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.jc_yataxi_prv1.DirectionsR
import com.pr7.jc_yataxi_prv1.data.model.refresh_token.RefreshToken
import com.pr7.jc_yataxi_prv1.data.model.refresh_token.RefreshTokenResponse
import com.pr7.jc_yataxi_prv1.data.model.regions.DistrictsResponse
import com.pr7.jc_yataxi_prv1.data.model.regions.RegionsResponse
import com.pr7.jc_yataxi_prv1.data.model.userinfo.UserInfoResponse
import com.pr7.jc_yataxi_prv1.data.network.RetrofitInstance
import kotlinx.coroutines.launch

class HomeViewModel constructor():ViewModel() {
    val api= RetrofitInstance.api
    val mlivedataUserInfo= MutableLiveData<Result<UserInfoResponse>>()
    val iscompleteduserinfo= MutableLiveData<Boolean>(false)

    val mlivedataRefreshtoken= MutableLiveData<Result<RefreshTokenResponse>>()
    val iscompletedrefreshtoken= MutableLiveData<Boolean>(false)

    val mlivedataGetAllRegions= MutableLiveData<Result<ArrayList<RegionsResponse>>>()
    val iscompletedgetallreg= MutableLiveData<Boolean>(false)

    val mlivedataGetAllDistricts= MutableLiveData<Result<ArrayList<DistrictsResponse>>>()

    //getdirections for user
    val mlivedataGetDirections= MutableLiveData<Result<ArrayList<DirectionsR>>>()
    val iscomplatedgetdirection= MutableLiveData<Boolean>(false)

    val changeregdis= MutableLiveData<Boolean>(true)

    val districtchoose= MutableLiveData<String?>(null)
    val districtfrom= MutableLiveData<String?>("")
    val districtto= MutableLiveData<String?>("")

    val regfromid=MutableLiveData<Int?>(0)
    val regtoid=MutableLiveData<Int?>(0)
    val disfromid=MutableLiveData<Int?>(0)
    val distoid=MutableLiveData<Int?>(0)



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

    fun refreshtoken(refreshtoken:String)=viewModelScope.launch {
        iscompletedrefreshtoken.postValue(true)
        try {
            val response=api.ferfeshtoken(RefreshToken("Bearer $refreshtoken"))
            if (response.isSuccessful){
                mlivedataRefreshtoken.postValue(Result.success(response.body()!!))

            }else{
                mlivedataRefreshtoken.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompletedrefreshtoken.postValue(false)

        }catch (e:Exception){
            iscompletedrefreshtoken.postValue(false)

        }

    }



    fun getAllRegions(token:String)=viewModelScope.launch {
        iscompletedgetallreg.postValue(true)
        try {
            val response=api.getAllRegions(token = "Bearer $token")
            if (response.isSuccessful){
                mlivedataGetAllRegions.postValue(Result.success(response.body()!!))

            }else{
                mlivedataGetAllRegions.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompletedgetallreg.postValue(false)

        }catch (e:Exception){
            iscompletedgetallreg.postValue(false)

        }

    }




    fun getAllDistrictss(token:String,id:Int)=viewModelScope.launch {

        try {
            val response=api.getAllDistrict(token = "Bearer $token", id = id)
            if (response.isSuccessful){
                mlivedataGetAllDistricts.postValue(Result.success(response.body()!!))

            }else{
                mlivedataGetAllDistricts.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }


        }catch (e:Exception){


        }

    }


    fun getalldirections(token :String, fromdisid:Int, todisid :Int, fromregid :Int, toregid :Int)=viewModelScope.launch {
        iscomplatedgetdirection.postValue(true)

        try {
            val response=api.getDriversList("Bearer $token" ,fromdisid , todisid, fromregid, toregid)
            if (response.isSuccessful){
                mlivedataGetDirections.postValue(Result.success(response.body()!!))

            }else{
                mlivedataGetDirections.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscomplatedgetdirection.postValue(false)


        }catch (e:Exception){


            iscomplatedgetdirection.postValue(false)

        }


    }
}