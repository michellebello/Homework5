package com.example.homework5.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AmphibianInfo (
    val name: String,
    val type: String,
    val description: String,
    @SerialName ("img_src")
    val imageSrc: String
)