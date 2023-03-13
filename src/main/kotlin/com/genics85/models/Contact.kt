package com.genics85.models

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val name:String,
    val country:String,
    val number: Int
)