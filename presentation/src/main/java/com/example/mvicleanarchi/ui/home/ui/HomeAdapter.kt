

package com.example.mvicleanarchi.ui.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entities.Post
import com.example.mvicleanarchi.databinding.ItemRepoBinding


class RepoAdapter() :
    ListAdapter<Post, RepoAdapter.MasterPassItemViewHolder>(
        DiffCallback
    ) {



    class MasterPassItemViewHolder(private var binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post

            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MasterPassItemViewHolder {

        return MasterPassItemViewHolder(
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MasterPassItemViewHolder, position: Int) {
        val masterPass = getItem(position)
        holder.bind(masterPass)
    }

}
