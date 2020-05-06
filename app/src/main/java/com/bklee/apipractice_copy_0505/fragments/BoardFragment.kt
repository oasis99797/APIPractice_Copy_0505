package com.bklee.apipractice_copy_0505.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bklee.apipractice_copy_0505.EditPostActivity
import com.bklee.apipractice_copy_0505.R
import com.bklee.apipractice_copy_0505.adapters.PostAdapter
import com.bklee.apipractice_copy_0505.datas.Post
import com.bklee.apipractice_copy_0505.utils.ConnectServer
import kotlinx.android.synthetic.main.fragment_board.*
import org.json.JSONObject

class BoardFragment : BaseFragment() {

    lateinit var postAdapter:PostAdapter
    val posts = ArrayList<Post>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun onResume() {
        super.onResume()


    }

    fun getPostsFromServer() {
        ConnectServer.getRequestPostList(mContext, object : ConnectServer.JsonReposeHandler{
            override fun onResponse(json: JSONObject) {

                val code = json.getInt("code")

                if(code == 200) {
                    posts.clear()

                    val data = json.getJSONObject("data")
                    val blackLists = data.getJSONArray("black_list")

                    for (i in 0..blackLists.length()-1) {
                        val postJson = blackLists.getJSONObject(i)
                        val postObject = Post.getPostFromJson(postJson)

                        posts.add(postObject)
                    }

                    activity?.runOnUiThread {
                        postAdapter.notifyDataSetChanged()
                    }

                    for(post in posts) {
                        Log.d("게시글제목", post.title)
                    }
                }
            }
        })
    }

    override fun setupEvents() {
        postBtn.setOnClickListener {
            val myIntent = Intent(mContext, EditPostActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {
        postAdapter = PostAdapter(mContext, R.layout.post_list_item, posts)
        postListView.adapter = postAdapter
    }


}