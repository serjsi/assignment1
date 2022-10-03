package com.shpp.ssierykh.assignment1.data.preferences.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.shpp.ssierykh.assignment1.activity_and_splash.MainActivity
import com.shpp.ssierykh.assignment1.data.preferences.DataStoreRepository
import com.shpp.ssierykh.assignment1.data.preferences.DataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(app)
}