package com.genics85.models

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Int?,
    var title:String,
    var body:String
    )