package com.emirhan.gdc_multicamp.service

import com.emirhan.gdc_multicamp.model.Post
import retrofit2.http.GET

interface PostApi {


    // Base_Url ' den sonra yer alacak url'nin devamını burada belirtiyoruz.
    @GET("multicamp/communities")
    suspend fun getTopHeadlines(): List<Post> // Bu işlem sonunda bize Post'lar tutan bir liste döndürülecek.

}