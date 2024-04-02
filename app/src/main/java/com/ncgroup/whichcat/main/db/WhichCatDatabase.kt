package com.ncgroup.whichcat.main.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ncgroup.whichcat.main.catBreeds.data.local.database.CatBreedDao
import com.ncgroup.whichcat.main.catBreeds.data.local.database.ImageEntityListConverter
import com.ncgroup.whichcat.main.catBreeds.data.local.database.WeightEntityListConverter
import com.ncgroup.whichcat.main.catBreeds.data.local.model.CatBreedEntity
import com.ncgroup.whichcat.main.cats.data.local.database.BreedEntityListConverter
import com.ncgroup.whichcat.main.cats.data.local.database.CatsDao
import com.ncgroup.whichcat.main.cats.data.local.model.CatsEntity


@Database(
    entities = [
        CatBreedEntity::class,
        CatsEntity::class
    ],
    version = 4
)
@TypeConverters(WeightEntityListConverter::class, ImageEntityListConverter::class, BreedEntityListConverter::class)
abstract class WhichCatDatabase : RoomDatabase() {

    abstract val catBreedDao: CatBreedDao
    abstract val catsDao: CatsDao

}