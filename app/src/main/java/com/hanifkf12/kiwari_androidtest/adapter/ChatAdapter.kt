package com.hanifkf12.kiwari_androidtest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanifkf12.kiwari_androidtest.R
import com.hanifkf12.kiwari_androidtest.model.Chat
import kotlinx.android.synthetic.main.item_received_message.view.*
import kotlinx.android.synthetic.main.item_sent_message.view.*
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(private val data : List<Chat>, private val myUsername : String) : RecyclerView.Adapter<ChatAdapter.ViewHolder>(){
    companion object{
        const val VIEW_TYPE_MESSAGE_SENT = 1
        const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }
    abstract class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(chat: Chat)
        fun Date?.calculateDate(onResult : (String)->Unit){
            this?.let {
                val sdf= SimpleDateFormat("dd MMM yyyy HH:mm", Locale.forLanguageTag("ID"))
                onResult(sdf.format(it))
            }
        }
    }
    class SentMessageViewHolder(itemView: View) : ViewHolder(itemView){
        override fun bind(chat: Chat) {
            chat.timestamps.calculateDate(){
                itemView.tv_timestamp_sent.text = it
            }
            itemView.tv_content_sent.text = chat.content
        }
    }

    class ReceivedMessageViewHolder(itemView: View) : ViewHolder(itemView){
        override fun bind(chat: Chat) {
            chat.timestamps!!.calculateDate(){
                itemView.tv_timestamp_received.text = it
            }
            itemView.tv_content_received.text = chat.content
        }

    }

    override fun getItemViewType(position: Int): Int {
        val chat = data[position]
        return when {
            chat.username.equals(myUsername) -> {
                VIEW_TYPE_MESSAGE_SENT
            }
            else -> {
                VIEW_TYPE_MESSAGE_RECEIVED
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType){
            VIEW_TYPE_MESSAGE_SENT->{
                SentMessageViewHolder(inflater.inflate(R.layout.item_sent_message,parent,false))
            }
            else->{
                ReceivedMessageViewHolder(inflater.inflate(R.layout.item_received_message,parent,false))
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder.itemViewType){
            VIEW_TYPE_MESSAGE_SENT ->{
                SentMessageViewHolder(holder.itemView).bind(data[position])
            }
            VIEW_TYPE_MESSAGE_RECEIVED->{
                ReceivedMessageViewHolder(holder.itemView).bind(data[position])
            }
        }
    }



}