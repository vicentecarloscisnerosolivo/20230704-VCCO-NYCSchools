package com.vcco.a20230704_vcco_nycschools.di.module

import com.vcco.a20230704_vcco_nycschools.repository.NYCSchoolsRepository
import com.vcco.a20230704_vcco_nycschools.repository.NYCSchoolsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    /**
     * Provides a unique instance of NYCSchoolsRepository
     * @param repository NYCSchoolsRepositoryImp *Injected*
     * @return NYCSchoolRepository
     */
    @Binds
    abstract fun provideNYCSchoolsRepository(repository: NYCSchoolsRepositoryImp): NYCSchoolsRepository
}