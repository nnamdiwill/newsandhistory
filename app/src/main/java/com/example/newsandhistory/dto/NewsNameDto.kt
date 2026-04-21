package com.example.newsandhistory.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NewsNameDto(
    var name:String
)