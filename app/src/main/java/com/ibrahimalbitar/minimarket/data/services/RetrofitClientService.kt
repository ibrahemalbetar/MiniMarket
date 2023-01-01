package com.ibrahimalbitar.minimarket.data.services

import com.ibrahimalbitar.minimarket.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientService {

    companion object {
        private val retrofitService =
            Retrofit.Builder()
                .baseUrl(BuildConfig.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        private fun getHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return retrofitService.create(serviceClass)
        }
    }

}