/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * api interface- connect to the api with the url and endpoint, querys for parameters
 */

package edu.quinnipiac.ser210.indeedjobsearch.apiData

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response

//interface
interface ApiInterface {

    @Headers(
        "X-RapidAPI-Host: indeed12.p.rapidapi.com",
        "X-RapidAPI-Key: 1ed9d7cc95mshde0f3be1c7ed937p103577jsn1d49282e63bd"
    )
    //using the search jobs endpoint
    @GET("jobs/search")
    suspend fun searchJobs(
        @Query("location") location: String,
        @Query("query") query: String,
        @Query("title") title: String?,
        @Query("company_name") companyName: String?
    ): Response<JobJSON>

    object ApiClient {
        //log the info
        private val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        //implement api
        val instance: ApiInterface by lazy {
            Retrofit.Builder()
                .baseUrl("https://indeed12.p.rapidapi.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }

    }
}