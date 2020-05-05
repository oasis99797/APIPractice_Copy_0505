package com.bklee.apipractice_copy_0505.utils

import android.content.Context
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ConnectServer {

    interface JsonReposeHandler {
        fun onResponse(json:JSONObject)
    }

    companion object {

        private val BASE_URL = "http://192.168.10.224:5000"

        fun postRequestLogin(
            context: Context,
            id: String,
            pw: String,
            handler: JsonReposeHandler?
        ) {

            val client = OkHttpClient()
            val urlStr = "${BASE_URL}/url"

            val formBody =FormBody.Builder()
                .add("login_id", id)
                .add("password", pw)
                .build()

            val request = Request.Builder()
                .url(urlStr)
                .post(formBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body!!.string()
                    val json = JSONObject(body)

                    handler?.onResponse(json)
                }
            })
        }

        fun getRequestMyInfo(context: Context, handler: JsonReposeHandler) {

            val client = OkHttpClient()
            val urlBuilder = "${BASE_URL}/my_info".toHttpUrlOrNull()!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("device_token", "임시기기토큰")
            urlBuilder.addEncodedQueryParameter("os", "android")

            val urlStr = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(urlStr)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body!!.string()
                    val json = JSONObject(body)

                    handler?.onResponse(json)
                }
            })
        }

        fun getRequestPostList(context: Context, handler: JsonReposeHandler?) {

            val client = OkHttpClient()
            val urlBuilder = "${BASE_URL}/my_info".toHttpUrlOrNull()!!.newBuilder()
//            urlBuilder.addEncodedQueryParameter("device_token", "임시기기토큰")
//            urlBuilder.addEncodedQueryParameter("os", "android")

            val urlStr = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(urlStr)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body!!.string()
                    val json = JSONObject(body)

                    handler?.onResponse(json)
                }
            })
        }

        fun postRequestBlackList(
            context: Context,
            title: String,
            phoneNum: String,
            content: String,
            handler: Any
        ) {

            val client = OkHttpClient()
            val urlStr = "${BASE_URL}/black_list"

            val formBody =FormBody.Builder()
                .add("title", title)
                .add("phone_num", phoneNum)
                .add("content", content)
                .build()

            val request = Request.Builder()
                .url(urlStr)
                .post(formBody)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body!!.string()
                    val json = JSONObject(body)

                    handler?.onResponse(json)
                }
            })
        }
    }
}