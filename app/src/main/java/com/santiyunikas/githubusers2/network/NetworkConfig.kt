package com.santiyunikas.githubusers2.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkConfig {

    private fun getInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build()
    }
    private var gson = GsonBuilder().setLenient().create()


    private fun connectionSearch(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(getInterceptor())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    fun serviceConnection(): Service = connectionSearch().create(Service::class.java)
    }