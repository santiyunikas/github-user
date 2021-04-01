package com.santiyunikas.githubusers2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiyunikas.githubusers2.R
import com.santiyunikas.githubusers2.model.UserGithub
import kotlinx.android.synthetic.main.item_user.view.*

class SearchUserAdapter(private val listUser: ArrayList<UserGithub>): RecyclerView.Adapter<SearchUserAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserGithub, position: Int)
    }
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserGithub, position: Int) {
            with(itemView){
                Glide.with(this)
                    .load(data.avatar_url)
                    .into(avatar)
                name.text = data.login
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(data, position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUser[position], position)
    }

    override fun getItemCount(): Int = listUser.size
}