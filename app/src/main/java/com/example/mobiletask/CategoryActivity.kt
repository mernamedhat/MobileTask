package com.example.mobiletask

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boxesaplication.prefrence.Preferences
import com.example.marvel.ui.home.CategoryViewModel
import com.example.mobiletask.api.Options
import com.example.mobiletask.databinding.ActivityCategoryBinding
import com.example.mobiletask.ui.options.ProcessTypeAdapter
import com.example.mobiletask.ui.category.SubCategoryAdapter
import com.example.mobiletask.ui.category.MainCategoryAdapter
import com.example.mobiletask.ui.options.SubCategoryOptionsAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.bottomsheetlayout.*


class CategoryActivity : AppCompatActivity(), MainCategoryAdapter.CallbackInterface,
    SubCategoryAdapter.CallbackInterface, SubCategoryOptionsAdapter.CallbackInterface ,ProcessTypeAdapter.CallbackInterface{
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var dialog: BottomSheetDialog
    var text:String=" "
    var id:Int=0
    private val MainCategoryAdapter by lazy { MainCategoryAdapter(this) }
    private val SubCategoryAdapter by lazy { SubCategoryAdapter(this, this) }
    private val ProcessTypeAdapter by lazy { ProcessTypeAdapter(this,this) }
    private val SubCategoryOptionsAdapter by lazy { SubCategoryOptionsAdapter(
        this,this) }
    private lateinit var preferences: Preferences
    val nameList: MutableList<Options> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog = BottomSheetDialog(this, R.style.bBottomSheetStyle)
        preferences = Preferences(this)
        dialog.setContentView(R.layout.bottomsheetlayout)

        getMainCategory()
        next.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
        submit.setOnClickListener {


        }

        input_phone2.setOnClickListener {
            dialog.choosetxt.setText("Main Category")
            getMainCategory()
            showDialog()


        }


    }

    private fun getMainCategory() {
        val nameList: MutableList<Categories> = mutableListOf()

        val viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        viewModel.getCategorys(
        ).observe(this) {

            nameList.addAll(it.data!!.categories)
            input_phone2.setText(it.data!!.categories.get(0).slug)
            if (it.data!!.categories.get(0).children.size != 0) {
                SubCat.visibility = View.VISIBLE
            }
            MainCategoryAdapter.differ.submitList(nameList)
            binding.apply {
                dialog.rec.apply {
                    layoutManager = LinearLayoutManager(this@CategoryActivity)
                    adapter = MainCategoryAdapter

                }
            }
        }

    }
    private fun getProcessType(CatId: Int) {
        val nameList: MutableList<com.example.mobiletask.api.Data> = mutableListOf()
        val viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        viewModel.getProcessType(
            CatId
        ).observe(this) {

            nameList.addAll(it.data)

            SubCategoryOptionsAdapter.differ.submitList(nameList)
            binding.apply {
                MainRc.apply {
                    layoutManager = LinearLayoutManager(this@CategoryActivity)
                    adapter = SubCategoryOptionsAdapter

                }
            }
        }


    }
    private fun showDialog() {
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }
    override fun passResultCallback(text: String, chickChild: String, id: Int?) {
        input_phone2.setText(text)
        dialog.dismiss()

        SubCat.visibility = if (chickChild.equals(1)) View.GONE
        else View.VISIBLE
        //setfristsubcat
        val viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        viewModel.getCategorys().observe(this) {
            sub_Cat.setText(it.data!!.categories.get(id!!).children.get(0).slug)
        }
        //Call Sub Category
        sub_Cat.setOnClickListener {
            dialog.choosetxt.setText("Sub Category")
            val nameList: MutableList<Children> = mutableListOf()
            val viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
            viewModel.getCategorys(
            ).observe(this) {
                nameList.addAll(it.data!!.categories.get(id!!).children)
                SubCategoryAdapter.differ.submitList(nameList)
                binding.apply {
                    dialog.rec.apply {
                        layoutManager = LinearLayoutManager(this@CategoryActivity)
                        adapter = SubCategoryAdapter
                    }
                }
            }
            showDialog()
        }
    }
    override fun SubCategoryCallback(text: String, chickChild: String, sub_id: Int?) {
        sub_Cat.setText(text)
        dialog.dismiss()
        getProcessType(sub_id!!)
    }

    override fun OptionsCallback(text: String?, position: Int?, hint: String?) {
        /////CallOptions Api
//        Log.d("zeco",position.toString())

        dialog.choosetxt.setText(hint)
        val viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        viewModel.getProcessType(preferences.getCategoryId()).observe(this) {
            nameList.addAll(it.data.get(position!!).options)
            ProcessTypeAdapter.differ.submitList(nameList)
            binding.apply {
                dialog.rec.apply {
                    layoutManager = LinearLayoutManager(this@CategoryActivity)
                    adapter = ProcessTypeAdapter
                }
            }
        }
        showDialog()

    }

    override fun OptionsCallback(textt: String, sub_id: Int?) {

        val viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        viewModel.getProcessType(preferences.getCategoryId()).observe(this) {

          //  Log.d("zecoooLog",it.data.get(0!!).options.get(0).slug.toString())
//            ProcessTypeAdapter.differ.submitList(nameList)
//            binding.apply {
//                dialog.rec.apply {
//                    layoutManager = LinearLayoutManager(this@CategoryActivity)
//                    adapter = ProcessTypeAdapter
//                }
//            }
        }


//        preferences.saveCategoryname(textt)
//
//        SubCategoryOptionsAdapter.notifyDataSetChanged()
//        binding.apply {
//            MainRc.apply {
//                layoutManager = LinearLayoutManager(this@CategoryActivity)
//                adapter = SubCategoryOptionsAdapterr
//
//            }
//        }
//        text=textt
//        id=sub_id!!
        dialog.dismiss()
    }


}