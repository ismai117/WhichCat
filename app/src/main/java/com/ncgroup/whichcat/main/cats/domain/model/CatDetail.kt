package com.ncgroup.whichcat.main.cats.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CatDetail(
    val id: Int?,
    val breedId: String,
    val breed: BreedCatDetail?,
    val url: String,
    val width: Int,
    val height: Int
): Parcelable

@Parcelize
data class BreedCatDetail(
    val id: String,
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
    val weight: WeightCatDetail,
    val wikipediaUrl: String
): Parcelable

@Parcelize
data class WeightCatDetail(
    val imperial: String,
    val metric: String
): Parcelable