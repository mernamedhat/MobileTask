package com.example.mobiletask.ui.submit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletask.SubmitModel
import com.example.mobiletask.databinding.CustomItemBinding


class SubmitAdapter (): RecyclerView.Adapter<SubmitAdapter.ViewHolder>() {
    private lateinit var binding: CustomItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= CustomItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }



    override fun getItemCount()=differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item : SubmitModel){
            binding.apply {
                textView.text =item.hint+":"+item.select

            }
        }

    }
    private val differCallback = object : DiffUtil.ItemCallback<SubmitModel>(){
        override fun areItemsTheSame(oldItem: SubmitModel, newItem: SubmitModel): Boolean {
            return  oldItem.id == newItem.id
        }
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: SubmitModel, newItem: SubmitModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
       holder.setIsRecyclable(false)
    }
}