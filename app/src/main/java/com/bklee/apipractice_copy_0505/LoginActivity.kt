package com.bklee.apipractice_copy_0505

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bklee.apipractice_copy_0505.datas.User
import com.bklee.apipractice_copy_0505.utils.ConnectServer
import com.bklee.apipractice_copy_0505.utils.ContextUtil
import com.bklee.apipractice_copy_0505.utils.GlobalData
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {

            val id = idEdt.text.toString()
            val pw = pwEdt.text.toString()

            ConnectServer.postRequestLogin(mContext, id, pw, object: ConnectServer.JsonReposeHandler {

                override fun onResponse(json: JSONObject) {

                    Log.d("로그인 응답", json.toString())

                    val code = json.getInt("code")

                    if (code == 200) {

                        val data = json.getJSONObject("data")
                        val user = json.getJSONObject("user")
                        val token = json.getString("token")

                        val newLoginUser = User.getUserFromJasonObject(user)

                        GlobalData.loginUser = newLoginUser
                        ContextUtil.setUserToken(mContext, token)

                        val myIntent = Intent(mContext, MyPageActivity::class.java)
                        startActivity(myIntent)

                    }
                    else {
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    override fun setValues() {
    }


}
