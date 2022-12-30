package com.canberkzdmr.moviesapplication.di

import com.canberkzdmr.moviesapplication.data.remote.MovieApi
import com.canberkzdmr.moviesapplication.data.repository.MovieRepositoryImpl
import com.canberkzdmr.moviesapplication.domain.repository.MovieRepository
import com.canberkzdmr.moviesapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created on 12/25/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieRepository(api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}