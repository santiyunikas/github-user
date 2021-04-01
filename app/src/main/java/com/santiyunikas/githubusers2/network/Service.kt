package com.santiyunikas.githubusers2.network

import com.santiyunikas.githubusers2.model.Objects
import retrofit2.Call
import retrofit2.http.*

interface Service {
    //for search
    @GET("/search/users?")
    fun search(
            @Query("q") uName: String
    ): Call<Objects>
}