package com.vcco.a20230704_vcco_nycschools.database.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE

/**
 * Dao<T> with common methods to be implemented
 */
interface DAO<T> {
    /**
     * Perform an insert of List of elements: T
     * @param elements
     */
    @Insert(onConflict = REPLACE)
    suspend fun insert(elements: List<T>)
}