package com.example.themoviedbexample.di

import com.example.themoviedbexample.data.dataSource.MovieDataSource
import com.example.themoviedbexample.data.dataSource.MovieDataSourceImpl
import com.example.themoviedbexample.data.network.MovieAPI
import com.example.themoviedbexample.data.repository.MovieRepositoryImpl
import com.example.themoviedbexample.domain.repository.MovieRepository
import com.example.themoviedbexample.domain.useCase.GetPopularMoviesUseCase
import com.example.themoviedbexample.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesMovieApi(retrofit: Retrofit): MovieAPI {
        return retrofit.create(MovieAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesMovieRemoteDataSource(
        api: MovieAPI
    ): MovieDataSource {
        return MovieDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesMovieRepository(
        movieRemoteDataSource: MovieDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(movieRemoteDataSource)
    }

    @Singleton
    @Provides
    fun providesGetMoviesUseCase(
        movieRepository: MovieRepository
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(movieRepository)
    }
}