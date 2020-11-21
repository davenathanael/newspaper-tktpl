package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.api

import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Story
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsService {
    @GET("topstories.json")
    suspend fun getTopStories(): Response<List<Long>>

    @GET("item/{itemId}.json")
    suspend fun getStoryItem(@Path("itemId") itemId: Long): Response<Story>

    companion object {
        private const val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

        fun create(): HackerNewsService {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HackerNewsService::class.java)
        }
    }
}