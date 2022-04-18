package com.anywhereapps.project.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item (
  var id   : Int = 0,
  var name : String? = null,
  var lat  : String? = null,
  var lng  : String? = null,
):Parcelable


