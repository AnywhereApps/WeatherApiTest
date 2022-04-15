package com.anywhereapps.project.network

import com.google.gson.annotations.SerializedName


data class Item (
  var id   : Int = 0,
  var name : String? = null,
  var lat  : String? = null,
  var lng  : String? = null,
)


