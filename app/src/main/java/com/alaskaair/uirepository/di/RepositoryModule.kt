package com.alaskaair.uirepository.di

import com.alaskaair.uirepository.data.remote.api.DesignTokenApi
import com.alaskaair.uirepository.data.repository.DesignTokenRepository
import com.alaskaair.uirepository.data.repository.DesignTokenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDesignTokenRepository(
        designTokenApi: DesignTokenApi
    ): DesignTokenRepository {
        return DesignTokenRepositoryImpl(designTokenApi)
    }

}