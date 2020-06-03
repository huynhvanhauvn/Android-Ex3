package com.sbro.androidex3

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
@Entity(tableName = "movie_information")
@Parcelize
data class Movie(
    @SerializedName("popularity") var popularity: Double,
    @SerializedName("vote_count") var vote_count: Int,
    @SerializedName("video") var video: Boolean,
    @SerializedName("poster_path") var poster_path: String,
    @SerializedName("id") @PrimaryKey(autoGenerate = true) var id: Int,
    @SerializedName("adult") var adult: Boolean,
    @SerializedName("backdrop_path") @ColumnInfo(defaultValue = "") var backdrop_path: String? = "",
    @SerializedName("original_language") var original_language: String,
    @SerializedName("original_title") var original_title: String,
    @SerializedName("genre_ids") var genre_ids: List<Int>,
    @SerializedName("title") var title: String,
    @SerializedName("vote_average") var vote_average: Double,
    @SerializedName("overview") var overview: String,
    @SerializedName("release_date") var release_date: String

) : Parcelable{

}