package com.example.baseappsample

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("users/john1jan")
    suspend fun getUserDetails(): Response<Any?>?
}