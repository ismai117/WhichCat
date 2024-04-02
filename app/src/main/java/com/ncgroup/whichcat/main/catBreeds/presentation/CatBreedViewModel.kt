package com.ncgroup.whichcat.main.catBreeds.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.ncgroup.whichcat.WhichCatsApplication
import com.ncgroup.whichcat.main.catBreeds.data.local.mapper.mapToDomainModel
import com.ncgroup.whichcat.main.catBreeds.data.local.model.CatBreedEntity
import kotlinx.coroutines.flow.map


class CatBreedViewModel(
    pager: Pager<Int, CatBreedEntity>
) : ViewModel() {

    val catBreeds = pager.flow
        .map {
            it.map { it.mapToDomainModel()}
        }.cachedIn(viewModelScope)


    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CatBreedViewModel(
                    WhichCatsApplication.catBreedModule.pager
                )
            }
        }
    }

}