package com.ncgroup.whichcat.main.catBreeds.data.local.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "catBreed_table")
data class CatBreedEntity(
    @PrimaryKey
    val id: Int?,
    val catBreedId: String,
    val adaptability: Int,
    val affectionLevel: Int,
    val altNames: String,
    val cfaUrl: String,
    val childFriendly: Int,
    val countryCode: String,
    val countryCodes: String,
    val description: String,
    val dogFriendly: Int,
    val energyLevel: Int,
    val experimental: Int,
    val grooming: Int,
    val hairless: Int,
    val healthIssues: Int,
    val hypoallergenic: Int,
    val imageEntity: ImageEntity,
    val indoor: Int,
    val intelligence: Int,
    val lap: Int,
    val lifeSpan: String,
    val name: String,
    val natural: Int,
    val origin: String,
    val rare: Int,
    val referenceImageId: String,
    val rex: Int,
    val sheddingLevel: Int,
    val shortLegs: Int,
    val socialNeeds: Int,
    val strangerFriendly: Int,
    val suppressedTail: Int,
    val temperament: String,
    val vcahospitalsUrl: String,
    val vetstreetUrl: String,
    val vocalisation: Int,
    val weightEntity: WeightEntity,
    val wikipediaUrl: String
)

@Serializable
data class ImageEntity(
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)

@Serializable
data class WeightEntity(
    val imperial: String,
    val metric: String
)
