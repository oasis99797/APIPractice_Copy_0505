package com.bklee.apipractice_copy_0505.fragments

import android.content.Context
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    abstract fun setupEvents()
    abstract fun setValues()
}