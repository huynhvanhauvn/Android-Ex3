package com.sbro.androidex3

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Data (
    @SerializedName("page") var page :Int,
    @SerializedName("results") var results : ArrayList<Movie>,
    @SerializedName("total_pages") var total_pages:Int,
    @SerializedName("total_results") var total_results:Int
) {
}