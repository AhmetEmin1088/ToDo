package com.ahmeteminyilmaz.todo.model

import com.google.gson.annotations.SerializedName

data class WeatherModel(

    @SerializedName("name")
    val cityName : String,
    @SerializedName("main")
    val main : TemperatureData,

)

data class TemperatureData(

    @SerializedName("temp")
    val temperature : Double,
    @SerializedName("temp_min")
    val temp_minimum : Double,
    @SerializedName("temp_max")
    val temp_maximum : Double,
    @SerializedName("humidity")
    val humidity : Double,

)