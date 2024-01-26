package com.pr7.jc_biztaxi_v4.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.jc_biztaxi_v4.data.model.directions2.DirectionsR2
import com.pr7.jc_biztaxi_v4.data.model.directions_active.DirectionsActiveResponse
import com.pr7.jc_biztaxi_v4.data.model.firebase_fcm.FirebaseFCM
import com.pr7.jc_biztaxi_v4.data.model.firebase_fcm.FirebaseFCMResponse
import com.pr7.jc_biztaxi_v4.data.model.newdirection.request.DirectionNew
import com.pr7.jc_biztaxi_v4.data.model.newdirection.response.DirectionNewResponse
import com.pr7.jc_biztaxi_v4.data.model.newseat.NewSeat
import com.pr7.jc_biztaxi_v4.data.model.newseat.NewSeatResponse
import com.pr7.jc_biztaxi_v4.data.model.orderseat.OrderSeat
import com.pr7.jc_biztaxi_v4.data.model.orderseat.OrderSeatResponse
import com.pr7.jc_biztaxi_v4.data.model.refresh_token.RefreshToken
import com.pr7.jc_biztaxi_v4.data.model.refresh_token.RefreshTokenResponse
import com.pr7.jc_biztaxi_v4.data.model.regions.DistrictsResponse
import com.pr7.jc_biztaxi_v4.data.model.regions.RegionsResponse
import com.pr7.jc_biztaxi_v4.data.model.userinfo.UserInfoResponse
import com.pr7.jc_biztaxi_v4.data.network.RetrofitInstance
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
    val mlivedataGetDirections= MutableLiveData<Result<DirectionsR2>>()
    val iscomplatedgetdirection= MutableLiveData<Boolean>(false)

    //new direction
    val mlivedatadirectionNew= MutableLiveData<Result<DirectionNewResponse>>()
    val iscompleteddirectionNew= MutableLiveData<Boolean>(false)
    //get active directions
    val mlivedataactiveDirection= MutableLiveData<Result<ArrayList<DirectionsActiveResponse>>>()
    val iscompletedactiveDirection= MutableLiveData<Boolean>(false)

    //FIREBASE
    val mlivedataFirebaseFCM= MutableLiveData<Result<FirebaseFCMResponse>>()

    //seatnew
    val mlivedatanewseat= MutableLiveData<Result<NewSeatResponse>>()
    //seatconfirmorder
    val mlivedataseatconfirm= MutableLiveData<Result<OrderSeatResponse>>()





    val changeregdis= MutableLiveData<Boolean>(true)

    val districtchoose= MutableLiveData<String?>(null)
    val districtfrom= MutableLiveData<String?>("")
    val districtto= MutableLiveData<String?>("")

    val regfromid=MutableLiveData<Int?>(0)
    val regtoid=MutableLiveData<Int?>(0)
    val disfromid=MutableLiveData<Int?>(0)
    val distoid=MutableLiveData<Int?>(0)

    val selectedtime1=MutableLiveData<String?>("")
    val selectedtime2=MutableLiveData<String?>("")
    val price=MutableLiveData<Int?>(0)
    val username=MutableLiveData<String?>("")

    //seatid
    val seatid1=MutableLiveData<Int?>(0)
    val seatid2=MutableLiveData<Int?>(0)
    val seatid3=MutableLiveData<Int?>(0)
    val seatid4=MutableLiveData<Int?>(0)
    //directionid
    val directionid=MutableLiveData<Int?>(0)



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
            val response=api.ferfeshtoken(RefreshToken("$refreshtoken"))
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


    fun directionNew(token :String,startdate:String,enddate:String,price:Int, fromdisid:Int, todisid :Int, fromregid :Int, toregid :Int)=viewModelScope.launch {
        iscompleteddirectionNew.postValue(true)

        try {
            val response=api.directionnew("Bearer $token" , DirectionNew(
                start_date = startdate,
                end_date = enddate,
                price = price,
                from_region = fromregid,
                from_district = fromdisid,
                to_region = toregid,
                to_district = todisid
            ))
            if (response.isSuccessful){
                mlivedatadirectionNew.postValue(Result.success(response.body()!!))

            }else{
                mlivedatadirectionNew.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompleteddirectionNew.postValue(false)


        }catch (e:Exception){


            iscompleteddirectionNew.postValue(false)

        }


    }




    fun getActiveDirections(token:String)=viewModelScope.launch {
        iscompletedactiveDirection.postValue(true)
        try {
            val response=api.getactiveDirections(token = "Bearer $token")
            if (response.isSuccessful){
                mlivedataactiveDirection.postValue(Result.success(response.body()!!))

            }else{
                mlivedataactiveDirection.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }
            iscompletedactiveDirection.postValue(false)

        }catch (e:Exception){
            iscompletedactiveDirection.postValue(false)

        }

    }


    fun firebaseFCM(token:String,fcmToken:String)=viewModelScope.launch {

        try {
            val response=api.firebaseFCM(token = "Bearer $token", FirebaseFCM(fcm_token = fcmToken))
            if (response.isSuccessful){
                mlivedataFirebaseFCM.postValue(Result.success(response.body()!!))

            }else{
                mlivedataFirebaseFCM.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }


        }catch (e:Exception){


        }

    }


    fun newSeat(token:String,newSeat: NewSeat)=viewModelScope.launch {

        try {
            val response=api.newSeat(token = "Bearer $token", newSeat)
            if (response.isSuccessful){
                mlivedatanewseat.postValue(Result.success(response.body()!!))

            }else{
                mlivedatanewseat.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }


        }catch (e:Exception){


        }

    }

    fun orderseatConfirm(token:String,id:Int,orderSeat: OrderSeat)=viewModelScope.launch {

        try {
            val response=api.confirmseatorder(token = "Bearer $token", id = id, orderSeat)
            if (response.isSuccessful){
                mlivedataseatconfirm.postValue(Result.success(response.body()!!))

            }else{
                mlivedataseatconfirm.postValue(Result.failure(Exception(response.errorBody()!!.string())))


            }


        }catch (e:Exception){


        }

    }

}