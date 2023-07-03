package com.vcco.a20230704_vcco_nycschools.di.module

import android.content.Context
import androidx.room.Room
import com.vcco.a20230704_vcco_nycschools.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideSchoolDao(database: AppDatabase) = database.schoolDao()

    @Provides
    fun provideSchoolSATScoresDao(database: AppDatabase) = database.schoolSchoolSATScoresDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "school_database"
        ).build()
    }
}