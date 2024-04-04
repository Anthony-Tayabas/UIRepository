package com.alaskaair.uirepository.di

import android.content.Context
import com.alaskaair.uirepository.UIRepositoryApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): UIRepositoryApplication {
        return app as UIRepositoryApplication
    }

    @Singleton
    @Provides
    fun provideContext(application: UIRepositoryApplication): Context {
        return application.applicationContext
    }

}