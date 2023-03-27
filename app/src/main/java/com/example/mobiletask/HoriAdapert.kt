package com.example.mobiletask

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletask.databinding.HoriRecItemBinding


class HoriAdapert : RecyclerView.Adapter<HoriAdapert.ViewHolder>(){
    private lateinit var binding: HoriRecItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= HoriRecItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
       // holder.setIsRecyclable(false)
    }

    override fun getItemCount()=differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item : visitor){
            binding.apply {

                name2.text = item.name
            }
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<visitor>(){
        override fun areItemsTheSame(oldItem: visitor, newItem: visitor): Boolean {
            return  oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: visitor, newItem: visitor): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

}

