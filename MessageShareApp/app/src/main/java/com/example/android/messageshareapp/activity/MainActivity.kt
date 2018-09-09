package com.example.android.messageshareapp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.android.messageshareapp.Constants
import com.example.android.messageshareapp.R
import com.example.android.messageshareapp.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonShwMsg.setOnClickListener {
            showToast(resources.getString(R.string.greeting))
        }

        btnSend.setOnClickListener {
            val message: String = etMessage.text.toString()
            showToast(message)

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(Constants.MSG_KEY, message)
            startActivity(intent)
        }

        btnShareWithApps.setOnClickListener {
            val message: String = etMessage.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Ти кабан?"))
        }

        btnToRecyclerView.setOnClickListener {
            val intent = Intent()

            startActivity(intent)
        }
    }
}
