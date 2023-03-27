package com.example.mobiletask.ui.options


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.boxesaplication.prefrence.Preferences
import com.example.mobiletask.databinding.SubCategoryItemBinding
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.bottomsheetlayout.*


class SubCategoryOptionsAdapter(private val callbackInterface: CallbackInterface,var context: Context): RecyclerView.Adapter<SubCategoryOptionsAdapter.ViewHolder>() {

    private lateinit var binding: SubCategoryItemBinding
    private lateinit var preferences: Preferences
    interface CallbackInterface {
        fun OptionsCallback(text: String?,sub_id:Int?=null,hint:String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= SubCategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }



    override fun getItemCount()=differ.currentList.size
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item : com.example.mobiletask.api.Data){
            binding.apply {
                //Log.d("MnMN",toString+id)
                preferences = Preferences(context)
                inputlayoutphone3.setHint( item.slug)
                if (item.options.size!=0){
                subCatitem.setText(preferences.getCategoryname())
                }
                subCatitem.setOnClickListener {
                    if(item.options.size!=0)
                    callbackInterface.OptionsCallback(item.options.get(0).slug,position,item.slug)
                }

            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<com.example.mobiletask.api.Data>(){
        override fun areItemsTheSame(oldItem: com.example.mobiletask.api.Data, newItem: com.example.mobiletask.api.Data): Boolean {
            return  oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: com.example.mobiletask.api.Data, newItem: com.example.mobiletask.api.Data): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }
}