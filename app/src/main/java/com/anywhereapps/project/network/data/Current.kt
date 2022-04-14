package com.example.example

import com.google.gson.annotations.SerializedName


data class Current (

  @SerializedName("dt"         ) var dt         : String?               = null,
  @SerializedName("sunrise"    ) var sunrise    : String?               = null,
  @SerializedName("sunset"     ) var sunset     : String?               = null,
  @SerializedName("temp"       ) var temp       : Double?            = null,
  @SerializedName("feels_like" ) var feelsLike  : Double?            = null,
  @SerializedName("pressure"   ) var pressure   : Int?               = null,
  @SerializedName("humidity"   ) var humidity   : Int?               = null,
  @SerializedName("dew_point"  ) var dewPoint   : Double?            = null,
  @SerializedName("uvi"        ) var uvi        : Double?            = null,
  @SerializedName("clouds"     ) var clouds     : Int?               = null,
  @SerializedName("visibility" ) var visibility : Int?               = null,
  @SerializedName("wind_speed" ) var windSpeed  : Double?            = null,
  @SerializedName("wind_deg"   ) var windDeg    : Int?               = null,
  @SerializedName("weather"    ) var weather    : ArrayList<Weather> = arrayListOf()

)