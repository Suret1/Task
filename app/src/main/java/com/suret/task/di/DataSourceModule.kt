package com.suret.task.di

import com.suret.task.data.api.API
import com.suret.task.data.repository.datasource.RemoteDataSource
import com.suret.task.data.repository.datasourceimpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun providesRemoteDataSource(api : API) : RemoteDataSource{
        return RemoteDataSourceImpl(api)
    }
}