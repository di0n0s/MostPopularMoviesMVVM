package com.example.sfcar.mostpopularmovies.injector.modules

import com.example.sfcar.mostpopularmovies.data.net.ApiConstants
import com.example.sfcar.mostpopularmovies.data.net.MoviesApiService
import com.example.sfcar.mostpopularmovies.data.net.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class MoviesApiModule {

    @Provides
    @Singleton
    fun provideMoviesApi(authInterceptor: AuthInterceptor): MoviesApiService {
        val httpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .connectTimeout(ApiConstants.TIMEOUT_CONNECTION_VALUE, TimeUnit.SECONDS)
                .readTimeout(ApiConstants.TIMEOUT_READ_VALUE, TimeUnit.SECONDS)
                .writeTimeout(ApiConstants.TIMEOUT_WRITE_VALUE, TimeUnit.SECONDS)
        val builder = Retrofit.Builder()
                .baseUrl(ApiConstants.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
        return builder.client(httpClient.build()).build().create(MoviesApiService::class.java)
    }
}