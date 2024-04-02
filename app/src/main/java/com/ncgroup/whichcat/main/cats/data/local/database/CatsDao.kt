package com.ncgroup.whichcat.main.cats.data.local.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ncgroup.whichcat.main.cats.data.local.model.CatsEntity


@Dao
interface CatsDao {

    @Insert
    suspend fun insertAll(catsEntity: List<CatsEntity>)

    @Query("SELECT * FROM cats_table")
    fun getCats(): PagingSource<Int, CatsEntity>

    @Query("DELETE FROM cats_table")
    suspend fun deleteAll()

}