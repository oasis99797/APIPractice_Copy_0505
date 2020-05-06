package com.bklee.apipractice_copy_0505.utils

import android.content.Context
import org.json.JSONObject

class ContextUtil {

    companion object{
        val prefname = "APIPracticePreference"
        val USER_TOKEN ="USER_TOKEN"

        fun setUserToken(context: Context, token: String) {
            val pref = context.getSharedPreferences(prefname, Context.MODE_PRIVATE)
            pref.edit().putString(USER_TOKEN, token).apply()
        }

        fun getUserToken(context: Context) : String {
            val pref =context.getSharedPreferences(prefname, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN, "")!!
        }
    }
}