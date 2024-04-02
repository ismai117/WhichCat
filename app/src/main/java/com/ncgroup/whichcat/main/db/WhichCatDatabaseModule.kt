package com.ncgroup.whichcat.main.db

import androidx.room.Room
import com.ncgroup.whichcat.WhichCatsApplication


class WhichCatDatabaseModule {

    val db: WhichCatDatabase by lazy {
        Room.databaseBuilder(
            context = WhichCatsApplication.INSTANCE,
            WhichCatDatabase::class.java,
            "whichCat.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}