package com.ahmeteminyilmaz.todo.service

import com.ahmeteminyilmaz.todo.model.WeatherModel
import com.ahmeteminyilmaz.todo.view.WeatherFragment
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherAPI {

    //48c1d3e2d53046a44b9c7ac34c63b2e0
    //https://api.openweathermap.org/data/2.5/weather?q=Kayseri,tr&APPID=48c1d3e2d53046a44b9c7ac34c63b2e0

    @GET("data/2.5/weather")
    fun getData(@Query("q") city : String, @Query("APPID") appid:String = "48c1d3e2d53046a44b9c7ac34c63b2e0") : Call<WeatherModel>

}