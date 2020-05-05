package com.bklee.apipractice_copy_0505.datas

import org.json.JSONObject
import java.util.*

class User {
    companion object {

        fun getUserFromJasonObject(json:JSONObject) : User {
            val parsedUser = User()

            parsedUser.id = json.getInt("id")
            parsedUser.loginId = json.getString("login_id")
            parsedUser.name = json.getString("name")
            parsedUser.phoneNum = json.getString("phone_num")
            parsedUser.memo = json.getString("memo")
            parsedUser.storeCategory = Category.getCategoryFromJson(json.getJSONObject("category"))

            return parsedUser
        }

    }

    var id = 0
    var loginId = ""
    var name = ""
    var phoneNum = ""
    var memo = ""

    var storeCategory = Category()
    var createdAt = Calendar.getInstance()
}