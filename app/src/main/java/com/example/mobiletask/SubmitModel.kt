package com.example.mobiletask

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SubmitModel(var id:Int?=null,var hint: String?=null, var select: String?=null):
    Parcelable
