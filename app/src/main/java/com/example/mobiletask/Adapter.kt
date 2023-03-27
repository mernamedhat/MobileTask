package com.example.mobiletask

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.mobiletask.databinding.RecItemBinding

//class Adapter (private val clickListener: ClickListener?, ) : ListAdapter<visitor, BaseViewHolder>(
//    USER_COMPARATOR
//) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
//        return ViewHolder(
//            StageItemBinding.inflate(
//                LayoutInflater.from(
//                    parent.context
//                ), parent, false
//            )
//        )
//    }
//
//
//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//        holder.onBind(position)
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return if (position == 0) 0 else 1
//
//    }
//
//    inner class ViewHolder(var binding: StageItemBinding) :
//        BaseViewHolder(binding.root) {
//        @RequiresApi(Build.VERSION_CODES.P)
//        override fun onBind(position: Int) {
//            val item = getItem(position)
//            binding.run {
//                model = item
//                index = position
////                listener = clickListener
////                bindMaterialImage(item)
//                imageView.setOnClickListener {
//                    clickListener?.onItemClick(it,item,position)
//                }
//            }
//        }
//
////        fun bindMaterialImage(item: visitor?) {
////            val con = binding.imageView.context
////            Glide.with(con)
////                .load(item?.media)
////                .into(object : DrawableImageViewTarget(binding.imageView) {
////                    override fun onResourceReady(
////                        resource: Drawable,
////                        transition: Transition<in Drawable>?
////                    ) {
////                        super.onResourceReady(resource, transition)
////                    }
////                })
////        }
//
//    }
//
//
//    companion object {
//        private val USER_COMPARATOR =
//            object : DiffUtil.ItemCallback<visitor>() {
//                override fun areItemsTheSame(
//                    oldItem: visitor,
//                    newItem: visitor
//                ): Boolean =
//                    newItem.id == oldItem.id
//
//                override fun areContentsTheSame(
//                    oldItem: visitor,
//                    newItem: visitor
//                ): Boolean =
//                    newItem == oldItem
//            }
//    }
//
//    interface ClickListener {
//        fun onItemClick(
//            v: View,
//            model: visitor,
//            position: Int
//        )
//    }
//}

class Adapter: RecyclerView.Adapter<Adapter.ViewHolder>(){
    private lateinit var binding: RecItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        binding= RecItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount()=differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root){
        fun setData(item : visitor){
            binding.apply {
                counter1.text=item.id.toString()
                name1.text = item.name
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

