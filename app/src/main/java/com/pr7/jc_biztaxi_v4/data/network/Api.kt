package com.pr7.jc_biztaxi_v4.data.network

import com.pr7.jc_biztaxi_v4.data.model.carinfo.CarListResponse
import com.pr7.jc_biztaxi_v4.data.model.changeuserinfo.ChangeUserInfo
import com.pr7.jc_biztaxi_v4.data.model.changeuserinfo.ChangeUserInfoResponse
import com.pr7.jc_biztaxi_v4.data.model.changeusertype.ChangeUserType
import com.pr7.jc_biztaxi_v4.data.model.changeusertype.ChangeUserTypeResponse
import com.pr7.jc_biztaxi_v4.data.model.directions2.DirectionsR2
import com.pr7.jc_biztaxi_v4.data.model.directions_active.DirectionsActiveResponse
import com.pr7.jc_biztaxi_v4.data.model.login.LoginUser
import com.pr7.jc_biztaxi_v4.data.model.login.LoginUserResponse
import com.pr7.jc_biztaxi_v4.data.model.newdirection.request.DirectionNew
import com.pr7.jc_biztaxi_v4.data.model.newdirection.response.DirectionNewResponse
import com.pr7.jc_biztaxi_v4.data.model.newdriver.DriverCarRegister
import com.pr7.jc_biztaxi_v4.data.model.newdriver.DriverCarRegisterResponse
import com.pr7.jc_biztaxi_v4.data.model.otp.OTPUser
import com.pr7.jc_biztaxi_v4.data.model.otp.OTPUserResponse
import com.pr7.jc_biztaxi_v4.data.model.refresh_token.RefreshToken
import com.pr7.jc_biztaxi_v4.data.model.refresh_token.RefreshTokenResponse
import com.pr7.jc_biztaxi_v4.data.model.regions.DistrictsResponse
import com.pr7.jc_biztaxi_v4.data.model.regions.RegionsResponse
import com.pr7.jc_biztaxi_v4.data.model.register.RegisterUser
import com.pr7.jc_biztaxi_v4.data.model.register.RegisterUserResponse
import com.pr7.jc_biztaxi_v4.data.model.userinfo.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {


    //https://yataxi.testing.uz/en/users/api/register/
    @POST("en/users/api/register/")
    suspend fun registerUser(@Body registerUser: RegisterUser):Response<RegisterUserResponse>

    //{{baseurl}}en/users/api/login/

    @POST("en/users/api/login/")
    suspend fun loginUser(@Body loginUser: LoginUser):Response<LoginUserResponse>

    //{{baseurl}}en/users/api/verify/
    @POST("en/users/api/verify/")
    suspend fun otpVerify(@Body otpUser: OTPUser):Response<OTPUserResponse>

    //https://yataxi.testing.uz/en/users/api/profile/
    @PATCH("en/users/api/profile/")
    suspend fun changeUsertype(
        @Header("Authorization") token :String,
        @Body changeUserType: ChangeUserType
    ):Response<ChangeUserTypeResponse>

    @GET("en/users/api/profile/")
    suspend fun getUserinfo(
        @Header("Authorization") token :String,
    ):Response<UserInfoResponse>

    @PUT("en/users/api/profile/")
    suspend fun changeUserinfo(
        @Header("Authorization") token :String,
        @Body changeUserInfo: ChangeUserInfo
    ):Response<ChangeUserInfoResponse>

    //https://yataxi.testing.uz/en/taxi/car-list/
    @GET("en/taxi/car-list/")
    suspend fun getCarlist(
        @Header("Authorization") token :String,
    ):Response<ArrayList<CarListResponse>>

    //https://yataxi.testing.uz/en/users/api/driver-create/
    @POST("en/users/api/driver-create/")
    suspend fun driverCarRegister(
        @Header("Authorization") token :String,
        @Body driverCarRegister: DriverCarRegister
    ):Response<DriverCarRegisterResponse>

    //https://yataxi.testing.uz/en/users/api/refresh-token/
    @POST("en/users/api/refresh-token/")
    suspend fun ferfeshtoken(
        @Body refreshToken: RefreshToken
    ):Response<RefreshTokenResponse>

    //https://yataxi.testing.uz/en/map/region-list/
    @GET("en/map/region-list/")
    suspend fun getAllRegions(@Header("Authorization") token :String):Response<ArrayList<RegionsResponse>>


    //https://yataxi.testing.uz/en/map/district-list/14/
    @GET("en/map/district-list/{id}/")
    suspend fun getAllDistrict(
        @Header("Authorization") token :String,
        @Path("id") id:Int
    ):Response<ArrayList<DistrictsResponse>>


    //https://yataxi.testing.uz/en/taxi/direction-list/?from_district=62&from_region=4&to_district=205&to_region=14
    @GET("en/taxi/direction-list/")
    suspend fun getDriversList(
        @Header("Authorization") token :String,
        @Query("from_district") fromdisid:Int,
        @Query("to_district") todisid:Int,
        @Query("from_region") fromregid:Int,
        @Query("to_region") toregid:Int,
    ):Response<DirectionsR2>

    //https://yataxi.testing.uz/en/taxi/direction-list-create/
    @POST("en/taxi/direction-create/")
    suspend fun directionnew(
        @Header("Authorization") token :String,
        @Body directionNew: DirectionNew
    ):Response<DirectionNewResponse>

    //https://yataxi.testing.uz/en/taxi/active-direction-list/
    @GET("en/taxi/active-direction-list/")
    suspend fun getactiveDirections(
        @Header("Authorization") token :String,
    ):Response<ArrayList<DirectionsActiveResponse>>




}