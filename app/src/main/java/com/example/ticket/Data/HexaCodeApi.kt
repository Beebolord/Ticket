package com.example.ticket.Data


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class HexaCodeApi {
    companion object {
        private val moshi  = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        const  val BASE_URL  = "https://colorofmyballs.herokuapp.com/"
        private val retrofit  = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Companion.BASE_URL)
            .build()
    }
    interface MarsApiService {
        @GET("firstBall")
        suspend fun getPosts() : List<HexaCode>
    }

    object MarsApi {
        val retrofitService : MarsApiService by lazy {
            retrofit.create(MarsApiService::class.java)
        }
    }

}

