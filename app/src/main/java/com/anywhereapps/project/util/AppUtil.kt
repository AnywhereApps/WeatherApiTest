package com.anywhereapps.project.util


import java.text.SimpleDateFormat


class AppUtil {

    companion object {

         fun getDate(date : String) : String {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            return simpleDateFormat.format(date)
        }

         fun getTime(time : String) : String {
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss.SSS")
            return simpleDateFormat.format(time)
        }

    }
}