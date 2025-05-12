package com.example.androidtheoneapi.di

import com.example.androidtheoneapi.api.TheOneApiAPI
import com.example.androidtheoneapi.repository.DefaultTheOneApiRepository
import com.example.androidtheoneapi.repository.TheOneApiRepository
import com.example.androidtheoneapi.util.Constants
import com.example.androidtheoneapi.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTheOneApi(): TheOneApiAPI {
        val logging = HttpLoggingInterceptor()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .authenticator(object : Authenticator {
                @Throws(IOException::class)
                override fun authenticate(route: Route?, response: Response): Request? {
                    if (response.request.header("Authorization") != null) {
                        return null // Give up, we've already attempted to authenticate.
                    }

                    val credential = Constants.API_KEY
                    return response.request.newBuilder()
                        .header("Authorization", "Bearer $credential")
                        .build()
                }
            })
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(TheOneApiAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: TheOneApiAPI): TheOneApiRepository {
        return DefaultTheOneApiRepository(api)
    }

}