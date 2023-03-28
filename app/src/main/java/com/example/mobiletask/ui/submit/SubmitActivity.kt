package com.example.mobiletask.ui.submit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiletask.CategoryActivity
import com.example.mobiletask.SubmitModel
import com.example.mobiletask.databinding.ActivitySubmitBinding
import kotlinx.android.synthetic.main.content_main.*


class SubmitActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubmitBinding
    var myList = ArrayList<SubmitModel>()
    var employeeModel:SubmitModel?=null
    private val Adapter by lazy{ SubmitAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySubmitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myList = intent.getSerializableExtra("mylist") as ArrayList<SubmitModel>
       Adapter.differ.submitList(myList)
        binding.apply {
            submitrec.apply {
                layoutManager= LinearLayoutManager(this@SubmitActivity)
                adapter=Adapter
            }
        }


    }
}