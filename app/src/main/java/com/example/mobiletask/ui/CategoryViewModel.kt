package com.example.marvel.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class CategoryViewModel : ViewModel() {

    fun getCategorys() = liveData {
        emit(CategoryRepository().getCategorys())
    }
    fun getProcessType(cat:Int) = liveData {
        emit(CategoryRepository().getProcessType(cat))
    }

}