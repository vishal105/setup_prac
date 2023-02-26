package com.example.setup_prac.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.setup_prac.data.remote.api.ApiService
import com.example.setup_prac.data.remote.api.RemoteData
import com.example.setup_prac.repo.DataRepository
import com.example.setup_prac.repo.DataRepositorySource
import com.example.setup_prac.utils.Network
import com.example.setup_prac.utils.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private const val timeOutConnect = 30
    private const val timeOutRead = 30

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesHeaderInterceptor(sharedPreferences: SharedPreferences) = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder().method(original.method, original.body).build()

        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .connectTimeout(timeOutConnect.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOutRead.toLong(), TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
                .client(okHttpClient).build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRemoteService(apiService: ApiService, networkConnectivity: NetworkConnectivity) =
        RemoteData(apiService, networkConnectivity)

    @Singleton
    @Provides
    fun provideDataRepository(
        remoteRepository: RemoteData,
        sharedPreferences: SharedPreferences,
        ioDispatcher: CoroutineContext
    ): DataRepositorySource = DataRepository(remoteRepository,sharedPreferences,ioDispatcher)

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

}