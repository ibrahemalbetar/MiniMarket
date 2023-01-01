package com.ibrahimalbitar.minimarket.data.services

import com.ibrahimalbitar.minimarket.data.model.ProductsResp
import retrofit2.http.GET

interface ApiService {

    @GET("default/dynamodb-writer")
    suspend fun getAllProducts() : ProductsResp
}