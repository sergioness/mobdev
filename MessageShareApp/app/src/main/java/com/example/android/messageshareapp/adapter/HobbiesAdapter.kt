package com.example.android.messageshareapp.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.messageshareapp.Hobby
import com.example.android.messageshareapp.R
import com.example.android.messageshareapp.showToast

class HobbiesAdapter(val context: Context, private val hobbies: List<Hobby>): RecyclerView.Adapter<HobbiesAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hobbies.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val hobby = hobbies[p1]
        p0.setData(hobby, p1)
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var currentHobby: Hobby? = null
        private var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {
                currentHobby?.let {
                    context.showToast(currentHobby!!.title + " clicked")
                }
            }
            itemView.imgShare.setOnClickListener{

                currentHobby?.let {
                    val message: String = "My hobby is " + currentHobby!!.title
                    val intent = Intent()
                    intent.action = Intent.ACTION_SEND
                    intent.putExtra(Intent.EXTRA_TEXT, message)
                    intent.type = "text/plain"

                    context.startActivity(Intent.createChooser(intent, "Ти кабан?"))
                }
            }
        }

        fun setData(hobby: Hobby?, position: Int) {
            hobby?.let{
                itemView.txvTitle.text = hobby.title
            }
            currentPosition = position
        }

    }
}