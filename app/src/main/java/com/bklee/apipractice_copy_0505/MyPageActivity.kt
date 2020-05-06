package com.bklee.apipractice_copy_0505

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.bklee.apipractice_copy_0505.utils.ContextUtil
import com.bklee.apipractice_copy_0505.utils.GlobalData
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.post_list_item.*

class MyPageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        logoutBtn.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", { dialog, which ->
                ContextUtil.setUserToken(mContext, "")

                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()
            })
            alert.setNegativeButton("취소", null)
            alert.show()
        }


    }

    override fun setValues() {
        nameTxt.text = GlobalData.loginUser?.name
        phoneNumTxt.text = GlobalData.loginUser?.phoneNum
        storeCategoryTxt.text = GlobalData.loginUser?.storeCategory?.title
    }


}
