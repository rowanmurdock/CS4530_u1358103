package com.example.KTORFunFacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FunFactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewFunFact (fact: FunFact)

    @Query("select * from funFacts")
    fun getAllFact(): Flow<List<FunFact>>
}