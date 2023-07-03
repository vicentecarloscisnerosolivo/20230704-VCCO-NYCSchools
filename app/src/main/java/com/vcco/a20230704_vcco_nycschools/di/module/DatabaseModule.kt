package com.vcco.a20230704_vcco_nycschools.di.module

import android.content.Context
import androidx.room.Room
import com.vcco.a20230704_vcco_nycschools.database.AppDatabase
import com.vcco.a20230704_vcco_nycschools.utils.database.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    //Provide SchoolDao to be consume in the databaseRepository
    @Provides
    fun provideSchoolDao(database: AppDatabase) = database.schoolDao()

    //Provide schoolSchoolSATScoresDao to be consume in the databaseRepository
    @Provides
    fun provideSchoolSATScoresDao(database: AppDatabase) = database.schoolSchoolSATScoresDao()

    //Provide Database instance, is singleton to always provide the same instance of the database
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DatabaseConstants.dbName
        ).build()
    }
}