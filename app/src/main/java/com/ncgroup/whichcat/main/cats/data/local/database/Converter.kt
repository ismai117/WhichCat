package com.ncgroup.whichcat.main.cats.data.local.database

import androidx.room.TypeConverter
import com.ncgroup.whichcat.main.cats.data.local.model.BreedEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class BreedEntityListConverter {
    private val json = Json

    @TypeConverter
    fun fromObj(breedEntity: List<BreedEntity>?): String? {
        return breedEntity?.let { json.encodeToString(it) }
    }

    @TypeConverter
    fun toObj(jsonString: String?): List<BreedEntity>? {
        return jsonString?.let { json.decodeFromString<List<BreedEntity>>(it) }
    }
}
