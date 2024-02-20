package com.example.starterproject.data

import retrofit2.http.GET

interface ApiService {
    @GET("/ab63567f-8d86-4443-aaa8-50a6cede1cae")
    fun getProducts(): List<ProductResponse>
}