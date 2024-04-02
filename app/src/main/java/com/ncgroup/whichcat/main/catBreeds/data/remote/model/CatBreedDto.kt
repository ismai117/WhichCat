package com.ncgroup.whichcat.main.catBreeds.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CatBreedDto(
    @SerialName("adaptability")
    val adaptability: Int? = null,
    @SerialName("affection_level")
    val affectionLevel: Int? = null,
    @SerialName("alt_names")
    val altNames: String? = null,
    @SerialName("cfa_url")
    val cfaUrl: String? = null,
    @SerialName("child_friendly")
    val childFriendly: Int? = null,
    @SerialName("country_code")
    val countryCode: String? = null,
    @SerialName("country_codes")
    val countryCodes: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("dog_friendly")
    val dogFriendly: Int? = null,
    @SerialName("energy_level")
    val energyLevel: Int? = null,
    @SerialName("experimental")
    val experimental: Int? = null,
    @SerialName("grooming")
    val grooming: Int? = null,
    @SerialName("hairless")
    val hairless: Int? = null,
    @SerialName("health_issues")
    val healthIssues: Int? = null,
    @SerialName("hypoallergenic")
    val hypoallergenic: Int? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("image")
    val imageDto: ImageDto? = null,
    @SerialName("indoor")
    val indoor: Int? = null,
    @SerialName("intelligence")
    val intelligence: Int? = null,
    @SerialName("lap")
    val lap: Int? = null,
    @SerialName("life_span")
    val lifeSpan: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("natural")
    val natural: Int? = null,
    @SerialName("origin")
    val origin: String? = null,
    @SerialName("rare")
    val rare: Int? = null,
    @SerialName("reference_image_id")
    val referenceImageId: String? = null,
    @SerialName("rex")
    val rex: Int? = null,
    @SerialName("shedding_level")
    val sheddingLevel: Int? = null,
    @SerialName("short_legs")
    val shortLegs: Int? = null,
    @SerialName("social_needs")
    val socialNeeds: Int? = null,
    @SerialName("stranger_friendly")
    val strangerFriendly: Int? = null,
    @SerialName("suppressed_tail")
    val suppressedTail: Int? = null,
    @SerialName("temperament")
    val temperament: String? = null,
    @SerialName("vcahospitals_url")
    val vcahospitalsUrl: String? = null,
    @SerialName("vetstreet_url")
    val vetstreetUrl: String? = null,
    @SerialName("vocalisation")
    val vocalisation: Int? = null,
    @SerialName("weight")
    val weightDto: WeightDto? = null,
    @SerialName("wikipedia_url")
    val wikipediaUrl: String? = null
)

@Serializable
data class ImageDto(
    @SerialName("height")
    val height: Int? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("width")
    val width: Int? = null
)

@Serializable
data class WeightDto(
    @SerialName("imperial")
    val imperial: String? = null,
    @SerialName("metric")
    val metric: String? = null
)