package com.ncgroup.whichcat.main.catBreeds.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ncgroup.whichcat.main.db.WhichCatDatabase
import com.ncgroup.whichcat.main.catBreeds.data.local.model.CatBreedEntity
import com.ncgroup.whichcat.main.catBreeds.data.mediator.CatBreedMediator
import com.ncgroup.whichcat.main.catBreeds.data.remote.service.CatBreedService


@OptIn(ExperimentalPagingApi::class)
class CatBreedModule(
    private val db: WhichCatDatabase
) {

    private val api: CatBreedService by lazy {
        CatBreedService()
    }

    val pager: Pager<Int, CatBreedEntity> by lazy {
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CatBreedMediator(
                db = db,
                api = api,

            ),
            pagingSourceFactory = {
                db.catBreedDao.getCatBreeds()
            }
        )
    }

}