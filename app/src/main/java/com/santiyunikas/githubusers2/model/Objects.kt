package com.santiyunikas.githubusers2.model

import com.google.gson.annotations.SerializedName

data class Objects(
    @SerializedName("total_count")
    val total_count: String,
    @SerializedName("items")
    val items: ArrayList<UserGithub>
)