package com.example.mobiletask.ui.category
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletask.Categories
import com.example.mobiletask.databinding.CustomItemBinding

class MainCategoryAdapter(private val callbackInterface: CallbackInterface): RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {
    private lateinit var binding:CustomItemBinding
    interface CallbackInterface {
        fun passResultCallback(text: String,chickChild:String,id:Int?=null)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= CustomItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }
    override fun getItemCount()=differ.currentList.size
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item : Categories){
            binding.apply {
                textView.text = item.slug

                itemView.setOnClickListener {
                    if(item.children.size!=0) {
                        callbackInterface.passResultCallback(item.slug.toString(), "1",position)
                    }else{
                        callbackInterface.passResultCallback(item.slug.toString(), "0")
                    }
                }

            }
        }

    }

    private val differCallback = object : DiffUtil.ItemCallback<Categories>(){
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return  oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }
}