package com.ncgroup.whichcat.main.catBreeds.data.mediator

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ncgroup.whichcat.main.db.WhichCatDatabase
import com.ncgroup.whichcat.main.catBreeds.data.local.mapper.mapFromDomainModel
import com.ncgroup.whichcat.main.catBreeds.data.local.model.CatBreedEntity
import com.ncgroup.whichcat.main.catBreeds.data.remote.mapper.mapToDomainModel
import com.ncgroup.whichcat.main.catBreeds.data.remote.model.CatBreedDto
import com.ncgroup.whichcat.main.catBreeds.data.remote.service.CatBreedService
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException


@OptIn(ExperimentalPagingApi::class)
class CatBreedMediator(
    private val db: WhichCatDatabase,
    private val api: CatBreedService
): RemoteMediator<Int, CatBreedEntity>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatBreedEntity>
    ): MediatorResult {
        return try {

            val loadKey = when(loadType){
                REFRESH -> 1
                PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null){
                        1
                    } else {
                        (lastItem.id!! / state.config.pageSize) + 1
                    }
                }
            }

            val catBreedDto= api.getCatBreeds(
                page = loadKey,
                limit = state.config.pageSize
            ).body<List<CatBreedDto>>()

            db.withTransaction {
                if(loadType == REFRESH){
                    db.catBreedDao.deleteAll()
                }
                val cats = catBreedDto.map { it.mapToDomainModel() }
                val entities = cats.map { it.mapFromDomainModel() }
                db.catBreedDao.insertAll(entities)
            }

            MediatorResult.Success(
                endOfPaginationReached = catBreedDto.isEmpty()
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
            MediatorResult.Error(Throwable("You've made too many requests!"))
        }
    }

}