package com.example.android.messageshareapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.messageshareapp.Constants
import com.example.android.messageshareapp.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            tvMsg.text = bundle.getString(Constants.MSG_KEY)
        }
    }
}
