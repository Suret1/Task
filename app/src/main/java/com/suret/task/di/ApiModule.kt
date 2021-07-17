package com.suret.task.di

import com.suret.task.data.api.API
import com.suret.task.data.api.MockAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesAPI(retrofit: Retrofit): API {
        val networkBehavior = NetworkBehavior.create()
        val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build()
        val behaviorDelegate : BehaviorDelegate<API> = mockRetrofit.create(API::class.java)
        return MockAPI(behaviorDelegate)
    }
}