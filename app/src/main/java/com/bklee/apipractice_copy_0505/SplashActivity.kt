package com.bklee.apipractice_copy_0505

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.bklee.apipractice_copy_0505.datas.User
import com.bklee.apipractice_copy_0505.utils.ConnectServer
import com.bklee.apipractice_copy_0505.utils.ContextUtil
import com.bklee.apipractice_copy_0505.utils.GlobalData
import org.json.JSONObject

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
    }

    override fun setValues() {

        Handler().postDelayed({

            if( ContextUtil.getUserToken(mContext) == "" ) {
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()
            }
            else {

                ConnectServer.getRequestMyInfo(mContext, object : ConnectServer.JsonReposeHandler {

                    override fun onResponse(json: JSONObject) {

                        Log.d("내정보응답", json.toString())

                        val data = json.getJSONObject("data")
                        val user = json.getJSONObject("user")
                        val nowUser = User.getUserFromJasonObject(user)

                        GlobalData.loginUser = nowUser

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                })

            }
        })
    }


}
