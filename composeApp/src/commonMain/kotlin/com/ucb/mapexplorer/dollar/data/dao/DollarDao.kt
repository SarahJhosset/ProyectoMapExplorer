package com.ucb.mapexplorer.dollar.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DollarDao {
    @Query("SELECT * FROM dollars")
    suspend fun getList(): List<DollarEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dollar: DollarEntity)

    @Query("DELETE FROM dollars")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDollars(lists: List<DollarEntity>)
}