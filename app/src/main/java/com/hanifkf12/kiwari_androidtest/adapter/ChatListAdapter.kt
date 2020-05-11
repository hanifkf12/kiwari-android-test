package com.hanifkf12.kiwari_androidtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hanifkf12.kiwari_androidtest.R
import com.hanifkf12.kiwari_androidtest.model.User
import kotlinx.android.synthetic.main.item_chat_list.view.*

class ChatListAdapter (private val data : List<User>) : RecyclerView.Adapter<ChatListAdapter.ViewHolder>(){
    private lateinit var listener: (User) -> Unit
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User){
            Glide.with(itemView).load(user.avatar).into(itemView.iv_ava_user)
            itemView.tv_user_list.text = user.name
            itemView.setOnClickListener {
                listener(user)
            }
        }
    }

    fun setOnItemClickListener(listener : (User) -> Unit){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

}