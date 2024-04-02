package com.ncgroup.whichcat.main.cats.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ncgroup.whichcat.WhichCatsApplication
import com.ncgroup.whichcat.main.cats.data.local.model.CatsEntity
import com.ncgroup.whichcat.main.cats.data.mediator.CatsMediator
import com.ncgroup.whichcat.main.cats.data.remote.service.CatsService


@OptIn(ExperimentalPagingApi::class)
class CatModule{

    val api: CatsService by lazy {
        CatsService()
    }

    val pager: Pager<Int, CatsEntity> by lazy {
           Pager(
            config = PagingConfig(20),
            remoteMediator = CatsMediator(
                db = WhichCatsApplication.whichCatDatabaseModule.db,
                api = api,
                breedId = "abys"
            ),
            pagingSourceFactory = {
                WhichCatsApplication.whichCatDatabaseModule.db.catsDao.getCats()
            }
        )
    }

}