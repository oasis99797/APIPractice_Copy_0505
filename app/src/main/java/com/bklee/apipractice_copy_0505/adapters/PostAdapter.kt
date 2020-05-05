package com.bklee.apipractice_copy_0505.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.bklee.apipractice_copy_0505.R
import com.bklee.apipractice_copy_0505.datas.Post
import java.text.SimpleDateFormat

class PostAdapter(context: Context, val resId: Int, list: ArrayList<Post>) : ArrayAdapter<Post>(context, resId, list){

    val mContext = context
    val mList = list
    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        tempRow?.let {

        }.let {
            tempRow = inf.inflate(resId, null)
        }

        val row = tempRow!!

        val titleTxt = row.findViewById<TextView>(R.id.titleTxt)
        val writeNameTxt = row.findViewById<TextView>(R.id.writeNameTxt)
        val createdAtTxt = row.findViewById<TextView>(R.id.createdAtTxt)
        val phoneNumTxt = row.findViewById<TextView>(R.id.phoneNumTxt)

        val postData = mList.get(position)

        titleTxt.text = postData.title
        phoneNumTxt.text = "(${postData.phoneNum})"
        writeNameTxt.text = "${postData.writer.name}(${postData.writer.storeCategory.title})"

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        createdAtTxt.text = sdf.format(postData.createdAt.time)

        return row
    }
}