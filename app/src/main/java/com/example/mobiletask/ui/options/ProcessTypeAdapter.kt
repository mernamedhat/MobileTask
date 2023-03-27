package com.example.mobiletask.ui.options

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletask.CategoryActivity

import com.example.mobiletask.api.Options
import com.example.mobiletask.databinding.CustomItemBinding

class ProcessTypeAdapter (private val callbackInterface: CallbackInterface, var context: Context): RecyclerView.Adapter<ProcessTypeAdapter.ViewHolder>() {
    private lateinit var binding: CustomItemBinding
    interface CallbackInterface {
        fun OptionsCallback(text: String,sub_id:Int?=null)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        binding= CustomItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }



    override fun getItemCount()=differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item : Options){
            binding.apply {
                textView.text = item.slug
                itemView.setOnClickListener {
                    callbackInterface.OptionsCallback(item.slug.toString(), item.id)

                }

            }
        }

    }
    private val differCallback = object : DiffUtil.ItemCallback<Options>(){
        override fun areItemsTheSame(oldItem: Options, newItem: Options): Boolean {
            return  oldItem.id == newItem.id
        }
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Options, newItem: Options): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
//        holder.setIsRecyclable(false)
    }
}