package com.example.newsandhistory.dataclasses

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
@Entity(tableName = "currentnews")
data class CurrentNews(
    var news: String, // URL
    var author : String,
    var country : String,
    var title:String,
    var description : String,
    var isUpdated: Boolean
) : Parcelable {
    var newsHeadline = news?.firstOrNull() ?: "Try again"
    var writer = author
    var territory = country

}

data class CurrentNewsWrapper(var news:List<CurrentNews>)