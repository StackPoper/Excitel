package com.task.excitel.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemInfo(
    val capitalName: String?,
    val code: String?,
    val flag: String?,
    val latLng: Array<Double>?,
    val name: String?,
    val population: Long?,
    val region: String?,
    val subregion: String?,

)