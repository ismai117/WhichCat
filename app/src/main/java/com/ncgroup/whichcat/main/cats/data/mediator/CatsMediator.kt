package com.ncgroup.whichcat.main.cats.data.mediator

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ncgroup.whichcat.main.cats.data.local.mapper.mapFromDomainModel
import com.ncgroup.whichcat.main.cats.data.local.mapper.mapFromDomainModelList
import com.ncgroup.whichcat.main.cats.data.local.model.CatsEntity
import com.ncgroup.whichcat.main.cats.data.remote.mapper.mapToDomainModel
import com.ncgroup.whichcat.main.cats.data.remote.model.CatsDto
import com.ncgroup.whichcat.main.cats.data.remote.service.CatsService
import com.ncgroup.whichcat.main.db.WhichCatDatabase
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.serialization.SerializationException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class CatsMediator(
    private val db: WhichCatDatabase,
    private val api: CatsService,
    private val breedId: String
) : RemoteMediator<Int, CatsEntity>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatsEntity>
    ): MediatorResult {
       return try {

            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> { return MediatorResult.Success(endOfPaginationReached = true)}
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null){
                        1
                    } else {
                        (lastItem.id!! / state.config.pageSize) + 1
                    }
                }
            }

           val catsDto = api.getCat(
               page = loadKey,
               limit = state.config.pageSize,
               breedId = breedId
           ).body<List<CatsDto>>()

           db.withTransaction {
               if (loadType == LoadType.REFRESH){
                   db.catsDao.deleteAll()
               }
               val cats = catsDto.map { it.mapToDomainModel() }
               val entries = cats.map { it.mapFromDomainModel() }
               db.catsDao.insertAll(entries)
           }

           MediatorResult.Success(
               endOfPaginationReached = catsDto.isEmpty()
           )

       } catch (e: ClientRequestException){
           MediatorResult.Error(e)
       } catch (e: ServerResponseException){
           MediatorResult.Error(e)
       } catch (e: IOException){
           MediatorResult.Error(e)
       } catch (e: SerializationException) {
           MediatorResult.Error(e)
       } catch (e: NoTransformationFoundException){
           e.printStackTrace()
           MediatorResult.Error(Throwable("You've made too many requests!, please try again in a few moments"))
       }

    }


}