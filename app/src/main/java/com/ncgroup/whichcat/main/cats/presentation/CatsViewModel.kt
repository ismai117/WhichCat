package com.ncgroup.whichcat.main.cats.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ncgroup.whichcat.WhichCatsApplication
import com.ncgroup.whichcat.main.cats.data.local.mapper.mapToDomainModel
import com.ncgroup.whichcat.main.cats.data.mediator.CatsMediator
import com.ncgroup.whichcat.main.cats.domain.mapper.mapToBreedCatDetail
import com.ncgroup.whichcat.main.cats.domain.model.CatDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class CatsViewModel : ViewModel(){

    val cats = MutableStateFlow<PagingData<CatDetail>>(PagingData.empty())

    @OptIn(ExperimentalPagingApi::class)
    fun getCats(breedId: String){
        viewModelScope.launch {
            val pager = Pager(
                config = PagingConfig(20),
                remoteMediator = CatsMediator(
                    db = WhichCatsApplication.whichCatDatabaseModule.db,
                    api = WhichCatsApplication.catModule.api,
                    breedId = breedId
                ),
                pagingSourceFactory = {
                    WhichCatsApplication.whichCatDatabaseModule.db.catsDao.getCats()
                }
            )
            pager.flow
                .map {
                    it.map {
                        CatDetail(
                            id = it.id,
                            breedId = it.breedId,
                            breed = it.mapToDomainModel().breeds.firstOrNull()?.mapToBreedCatDetail(),
                            url = it.url,
                            width = it.width,
                            height = it.height
                        )
                    }
                }
                .collect {
                    cats.value = it
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CatsViewModel()
            }
        }
    }

}

