package com.anywhereapps.project.util

import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat


class AppUtil {

    companion object {
        private const val  IN_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val  OUT_TIME_FORMAT = "MMM dd,yyyy 'at'  hh:mm aaa"

  /*      fun getTime(date : String): String {

            val inTimeFormatter = SimpleDateFormat(IN_TIME_FORMAT)
            val date = inTimeFormatter.parse(date)

            val outFormatter = SimpleDateFormat(OUT_TIME_FORMAT)
            return outFormatter.format(date).toString()
        }*/

         fun getDate(date : String) : String {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            return simpleDateFormat.format(date)
        }

         fun getTime(time : String) : String {
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss.SSS")
            return simpleDateFormat.format(time)
        }

        fun shareContent(context : Context?, title : String, detail : String){
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, detail)
                putExtra(Intent.EXTRA_TITLE, title)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context?.startActivity(shareIntent)
        }

    }
}