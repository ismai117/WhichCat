package com.ncgroup.whichcat.main.catBreeds.data.remote.mapper

import com.ncgroup.whichcat.main.catBreeds.data.remote.model.CatBreedDto
import com.ncgroup.whichcat.main.catBreeds.domain.model.CatBreed
import com.ncgroup.whichcat.main.catBreeds.domain.model.Image
import com.ncgroup.whichcat.main.catBreeds.domain.model.Weight


fun CatBreedDto.mapToDomainModel(): CatBreed {
    return CatBreed(
        id = 0,
        catBreedId = this.id.orEmpty(),
        adaptability = this.adaptability ?: 0,
        affectionLevel = this.affectionLevel ?: 0,
        altNames = this.altNames.orEmpty(),
        cfaUrl = this.cfaUrl.orEmpty(),
        childFriendly = this.childFriendly ?: 0,
        countryCode = this.countryCode.orEmpty(),
        countryCodes = this.countryCodes.orEmpty(),
        description = this.description.orEmpty(),
        dogFriendly = this.dogFriendly ?: 0,
        energyLevel = this.energyLevel ?: 0,
        experimental = this.experimental ?: 0,
        grooming = this.grooming ?: 0,
        hairless = this.hairless ?: 0,
        healthIssues = this.healthIssues ?: 0,
        hypoallergenic = this.hypoallergenic ?: 0,
        image = Image(
            id = this.imageDto?.id.orEmpty(),
            url = this.imageDto?.url.orEmpty(),
            width = this.imageDto?.width ?: 0,
            height = this.imageDto?.height ?: 0,
        ),
        indoor = this.indoor ?: 0,
        intelligence = this.intelligence ?: 0,
        lap = this.lap ?: 0,
        lifeSpan = this.lifeSpan.orEmpty(),
        name = this.name.orEmpty(),
        natural = this.natural ?: 0,
        origin = this.origin.orEmpty(),
        rare = this.rare ?: 0,
        referenceImageId = this.referenceImageId.orEmpty(),
        rex = this.rex ?: 0,
        sheddingLevel = this.sheddingLevel ?: 0,
        shortLegs = this.shortLegs ?: 0,
        socialNeeds = this.socialNeeds ?: 0,
        strangerFriendly = this.strangerFriendly ?: 0,
        suppressedTail = this.suppressedTail ?: 0,
        temperament = this.temperament.orEmpty(),
        vcahospitalsUrl = this.vcahospitalsUrl.orEmpty(),
        vetstreetUrl = this.vetstreetUrl.orEmpty(),
        vocalisation = this.vocalisation ?: 0,
        weight = Weight(
            imperial = this.weightDto?.imperial.orEmpty(),
            metric = this.weightDto?.metric.orEmpty()
        ),
        wikipediaUrl = this.wikipediaUrl.orEmpty()
    )
}

fun List<CatBreedDto>.mapToDomainModelList(): List<CatBreed> {
    return this.mapToDomainModelList()
}