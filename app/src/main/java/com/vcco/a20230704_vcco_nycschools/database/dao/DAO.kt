package com.vcco.a20230704_vcco_nycschools.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE

interface DAO<T> {
    @Insert(onConflict = REPLACE)
    suspend fun insert(elements: List<T>)
}