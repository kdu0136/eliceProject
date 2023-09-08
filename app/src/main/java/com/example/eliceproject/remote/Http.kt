package com.example.eliceproject.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Http {
    fun createOkHttpClient(
        httpTimeOut: Long = 10L
    ): OkHttpClient {
        // redirect & ssl redirect 허용
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)

        // log 레벨 설정
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // time out 설정 후 http client 반환
        return httpClient
            .connectTimeout(httpTimeOut, TimeUnit.SECONDS)
            .readTimeout(httpTimeOut, TimeUnit.SECONDS)
            .writeTimeout(httpTimeOut, TimeUnit.SECONDS)
            .callTimeout(httpTimeOut, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .build()
    }

    // retrofit 을 이용한 API 요청 인터페이스 생성
    internal inline fun <reified T> createRetrofit(baseUrl: String, client: OkHttpClient): T {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(T::class.java)
    }
}
