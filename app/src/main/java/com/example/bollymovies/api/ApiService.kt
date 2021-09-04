package com.example.bollymovies.api

import com.example.bollymovies.BuildConfig
import com.example.bollymovies.utils.ConstantsApp.Api.API_TOKEN
import com.example.bollymovies.utils.ConstantsApp.Api.API_TOKEN_KEY
import com.example.bollymovies.utils.ConstantsApp.Api.QUERY_PARAM_CREDITS_LABEL
import com.example.bollymovies.utils.ConstantsApp.Api.QUERY_PARAM_CREDITS_VALUE
import com.example.bollymovies.utils.ConstantsApp.Api.QUERY_PARAM_LANGUAGE_KEY
import com.example.bollymovies.utils.ConstantsApp.Api.QUERY_PARAM_LANGUAGE_VALUE
import com.example.bollymovies.utils.ConstantsApp.Api.QUERY_PARAM_ORIGINAL_LANGUAGE_LABEL
import com.example.bollymovies.utils.ConstantsApp.Api.QUERY_PARAM_ORIGINAL_LANGUAGE_VALUE
import com.example.bollymovies.utils.ConstantsApp.Api.QUERY_PARAM_SORT_BY_LABEL
import com.example.bollymovies.utils.ConstantsApp.Api.QUERY_PARAM_SORT_BY_VALUE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    val tmdbApi: TMDBApi = getTMDBApiClient().create(TMDBApi::class.java)
    val tmdbApiWithCast: TMDBApi = getTMDBApiWithCast().create(TMDBApi::class.java)

    fun getTMDBApiClient(): Retrofit {
        return Retrofit.Builder()
            //.baseUrl(BuildConfig.BASE_URL)
            .baseUrl("https://api.themoviedb.org/3/")
            .client(getInterceptorClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getTMDBApiWithCast(): Retrofit {
        return Retrofit.Builder()
            //.baseUrl(BuildConfig.BASE_URL)
            .baseUrl("https://api.themoviedb.org/3/")
            .client(getInterceptorWithCast())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getInterceptorClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter(API_TOKEN_KEY, API_TOKEN)
                    .addQueryParameter(QUERY_PARAM_LANGUAGE_KEY, QUERY_PARAM_LANGUAGE_VALUE)
                    .addQueryParameter(QUERY_PARAM_ORIGINAL_LANGUAGE_LABEL, QUERY_PARAM_ORIGINAL_LANGUAGE_VALUE)
                    .addQueryParameter(QUERY_PARAM_SORT_BY_LABEL, QUERY_PARAM_SORT_BY_VALUE)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
        return interceptor.build()
    }

    private fun getInterceptorWithCast(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        val interceptor = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter(API_TOKEN_KEY, API_TOKEN)
                    .addQueryParameter(QUERY_PARAM_LANGUAGE_KEY, QUERY_PARAM_LANGUAGE_VALUE)
                    .addQueryParameter(QUERY_PARAM_ORIGINAL_LANGUAGE_LABEL, QUERY_PARAM_ORIGINAL_LANGUAGE_VALUE)
                    .addQueryParameter(QUERY_PARAM_CREDITS_LABEL, QUERY_PARAM_CREDITS_VALUE)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
        return interceptor.build()
    }

}