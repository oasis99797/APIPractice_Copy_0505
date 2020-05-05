package com.bklee.apipractice_copy_0505.datas

import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Post {
    companion object {

        fun getPostFromJson(json:JSONObject) : Post {

            val p = Post()

            p.phoneNum = json.getString("phone_num")
            p.title = json.getString("title")
            p.content = json.getString("content")

            val userJson = json.getJSONObject("writer")
            p.writer = User.getUserFromJasonObject(userJson)

            val createdStr = json.getString("created_at")

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            p.createdAt.time = sdf.parse(createdStr)

            return p
        }
    }

    var phoneNum = ""
    var title = ""
    var content = ""

    var writer = User()
    var createdAt = Calendar.getInstance()
}