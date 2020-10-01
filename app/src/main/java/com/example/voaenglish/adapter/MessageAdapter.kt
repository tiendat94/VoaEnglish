package com.example.voaenglish.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.voaenglish.BR
import com.example.voaenglish.databinding.MessageListRowBinding
import com.example.voaenglish.model.Message
import com.example.voaenglish.ui.login.message.MessageViewModel
import kotlinx.android.synthetic.main.message_list_row.view.*

class MessageAdapter(private val messageViewModel: MessageViewModel) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    var messageList: List<Message> = emptyList()
    var isSelectedALL: Boolean? = false

    class MessageViewHolder constructor(private val dataBinding: ViewDataBinding, private val messageViewModel: MessageViewModel) : RecyclerView.ViewHolder(dataBinding.root) {

        fun setup(message: Message?) {
            dataBinding.setVariable(BR.message, message)
            Glide.with(itemView.context).load(message?.picture).into(itemView.icon_profile)
            dataBinding.executePendingBindings()

            itemView.setOnClickListener {

            }
        }
    }

    fun selectAll() {
        isSelectedALL = true
        notifyDataSetChanged()
    }

    fun unSelectAll() {
        isSelectedALL = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val dataBinding = MessageListRowBinding.inflate(inflater, parent, false)
        return MessageViewHolder(dataBinding, messageViewModel)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder?.setup(messageList[position])
        if (!isSelectedALL!!) {
            holder?.itemView?.checkbox_message?.isChecked = false
        } else {
            holder?.itemView?.checkbox_message?.isChecked = true
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun updateListMessage(messageList: List<Message>) {
        this.messageList = messageList
        notifyDataSetChanged()
    }
}