package com.emirhan.gdc_multicamp.service

import android.provider.SyncStateContract
import com.emirhan.gdc_multicamp.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create

object RetrofitProvider {

    private val contentType: MediaType = MediaType.get("application/json")  // -> Medya tipimizi belirtiyoruz. Json çekeceğimiz için "application/json" kullandık.

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)    // -> Ana url'mizi burada belirtiyoruz.
        .addConverterFactory(Json {                                         // -> Çekilen Json nesnesini istediğimiz formata dönüştürmek için converterFactory ekliyoruz.
            ignoreUnknownKeys = true
            isLenient = true
            allowSpecialFloatingPointValues = true
            encodeDefaults = false
        }.asConverterFactory(contentType))             // -> Medyanın tipini de belirttikten sonra retrofit nesnesini build ediyoruz.
        .build()

    val newsApi = retrofit.create<PostApi>()           // -> Oluşturduğumuz PostApi Interface'ini burada retrofit nesnemiz ile oluşturuyoruz ve RetrofitProvider kullanıma hazır hale geliyor.

}