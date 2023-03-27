package com.example.mobiletask.ui.category

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.boxesaplication.prefrence.Preferences
import com.example.mobiletask.Children

import com.example.mobiletask.databinding.CustomItemBinding

class SubCategoryAdapter (private val callbackInterface: CallbackInterface, var context:Context): RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {
    private lateinit var binding: CustomItemBinding
    private lateinit var preferences: Preferences
    interface CallbackInterface {
        fun SubCategoryCallback(text: String,chickChild:String,sub_id:Int?=null)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding= CustomItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }



    override fun getItemCount()=differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){

        fun setData(item : Children){
            binding.apply {
                preferences = Preferences(context)
                textView.text = item.slug
                itemView.setOnClickListener {
                    callbackInterface.SubCategoryCallback(item.slug.toString(), "1",item.id)
                    preferences.saveCategoryId(item.id!!)
                }

            }
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<Children>(){
        override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
            return  oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

}