package com.bklee.apipractice_copy_0505

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bklee.apipractice_copy_0505.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_edit_post.*
import org.json.JSONObject

class EditPostActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_post)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        postCompleteBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("게시글 등록")
            alert.setMessage("게시글을 등록 하시겠습니까?")

            alert.setPositiveButton("확인", {dialog, which ->

                val title = titleEdt.text.toString()
                val phoneNum = phoneNumEdt.text.toString()
                val content = contentEdt.text.toString()

                ConnectServer.postRequestBlackList(mContext, title, phoneNum, content, object : ConnectServer.JsonReposeHandler{
                    override fun onResponse(json: JSONObject) {

                        val code = json.getInt("code")

                        if (code == 200) {
                            runOnUiThread {
                                Toast.makeText(mContext, "게시글 작성완료", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                    }
                })
            })

            alert.setNegativeButton("취소", null)
            alert.show()
        }
    }

    override fun setValues() {
    }


}
