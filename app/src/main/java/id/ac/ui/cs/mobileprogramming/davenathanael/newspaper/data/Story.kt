package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data

import com.google.gson.annotations.SerializedName

data class Story(
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("by") val by: String,
    @field:SerializedName("time") val time: Long,
    @field:SerializedName("descendants") val descendants: Long
)
