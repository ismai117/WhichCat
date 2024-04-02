package com.ncgroup.whichcat.main.catBreeds.data.local.database

import androidx.room.TypeConverter
import com.ncgroup.whichcat.main.catBreeds.data.local.model.ImageEntity
import com.ncgroup.whichcat.main.catBreeds.data.local.model.WeightEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class WeightEntityListConverter {
    private val json = Json

    @TypeConverter
    fun fromObj(weightEntity: WeightEntity?): String? {
        return weightEntity?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toObj(jsonString: String?): WeightEntity? {
        return jsonString?.let { json.decodeFromString<WeightEntity>(it) }
    }
}

class ImageEntityListConverter {
    private val json = Json

    @TypeConverter
    fun fromObj(imageEntity: ImageEntity?): String? {
        return imageEntity?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toObj(jsonString: String?): ImageEntity? {
        return jsonString?.let { json.decodeFromString<ImageEntity>(it) }
    }
}
