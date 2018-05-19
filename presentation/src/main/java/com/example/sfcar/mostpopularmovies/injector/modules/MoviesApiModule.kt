package com.example.sfcar.mostpopularmovies.injector.modules

import com.example.sfcar.mostpopularmovies.UIThread
import com.example.sfcar.mostpopularmovies.data.net.ApiConstants
import com.example.sfcar.mostpopularmovies.data.net.MoviesApiService
import com.example.sfcar.mostpopularmovies.data.net.interceptor.AuthInterceptor
import com.example.sfcar.mostpopularmovies.data.repository.MovieRepositoryImp
import com.example.sfcar.mostpopularmovies.data.repository.datasource.MoviesDataStore
import com.example.sfcar.mostpopularmovies.data.repository.datasource.MoviesDataStoreImpl
import com.example.sfcar.mostpopularmovies.domain.executor.PostExecutionThread
import com.example.sfcar.mostpopularmovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        return builder.client(httpClient.build()).build().create(MoviesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroRepository(movieRepositoryImp: MovieRepositoryImp): MovieRepository = movieRepositoryImp


    @Provides
    @Singleton
    fun provideHeroDataStore(moviesDataStoreImpl: MoviesDataStoreImpl): MoviesDataStore = moviesDataStoreImpl


    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread
}