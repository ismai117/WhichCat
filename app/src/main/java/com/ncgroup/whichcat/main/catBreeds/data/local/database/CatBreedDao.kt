package com.ncgroup.whichcat.main.catBreeds.data.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ncgroup.whichcat.main.catBreeds.data.local.model.CatBreedEntity


@Dao
interface CatBreedDao {

    @Insert
    suspend fun insertAll(catBreedEntity: List<CatBreedEntity>)

    @Query("SELECT * FROM catBreed_table")
    fun getCatBreeds(): PagingSource<Int, CatBreedEntity>

    @Query("DELETE FROM catBreed_table")
    suspend fun deleteAll()
    
}