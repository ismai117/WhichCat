package com.ncgroup.whichcat

import android.app.Application
import com.ncgroup.whichcat.main.catBreeds.di.CatBreedModule
import com.ncgroup.whichcat.main.cats.di.CatModule
import com.ncgroup.whichcat.main.db.WhichCatDatabaseModule


class WhichCatsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        whichCatDatabaseModule = WhichCatDatabaseModule()
        catBreedModule = CatBreedModule(whichCatDatabaseModule.db)
        catModule = CatModule()
    }

    companion object {
        lateinit var INSTANCE: WhichCatsApplication
        lateinit var whichCatDatabaseModule: WhichCatDatabaseModule
        lateinit var catBreedModule: CatBreedModule
        lateinit var catModule: CatModule
    }

}