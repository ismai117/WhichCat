package com.ncgroup.whichcat.main.catBreeds.data.local.mapper

import com.ncgroup.whichcat.main.catBreeds.data.local.model.CatBreedEntity
import com.ncgroup.whichcat.main.catBreeds.data.local.model.ImageEntity
import com.ncgroup.whichcat.main.catBreeds.data.local.model.WeightEntity
import com.ncgroup.whichcat.main.catBreeds.domain.model.CatBreed
import com.ncgroup.whichcat.main.catBreeds.domain.model.Image
import com.ncgroup.whichcat.main.catBreeds.domain.model.Weight


fun CatBreedEntity.mapToDomainModel(): CatBreed {
    return CatBreed(
        id = this.id,
        catBreedId = this.catBreedId,
        adaptability = this.adaptability,
        affectionLevel = this.affectionLevel,
        altNames = this.altNames,
        cfaUrl = this.cfaUrl,
        childFriendly = this.childFriendly,
        countryCode = this.countryCode,
        countryCodes = this.countryCodes,
        description = this.description,
        dogFriendly = this.dogFriendly,
        energyLevel = this.energyLevel,
        experimental = this.experimental,
        grooming = this.grooming,
        hairless = this.hairless,
        healthIssues = this.healthIssues,
        hypoallergenic = this.hypoallergenic,
        image = Image(
            id = this.imageEntity.id,
            url = this.imageEntity.url,
            width = this.imageEntity.width,
            height = this.imageEntity.height,
        ),
        indoor = this.indoor,
        intelligence = this.intelligence,
        lap = this.lap,
        lifeSpan = this.lifeSpan,
        name = this.name,
        natural = this.natural,
        origin = this.origin,
        rare = this.rare,
        referenceImageId = this.referenceImageId,
        rex = this.rex,
        sheddingLevel = this.sheddingLevel,
        shortLegs = this.shortLegs,
        socialNeeds = this.socialNeeds,
        strangerFriendly = this.strangerFriendly,
        suppressedTail = this.suppressedTail,
        temperament = this.temperament,
        vcahospitalsUrl = this.vcahospitalsUrl,
        vetstreetUrl = this.vetstreetUrl,
        vocalisation = this.vocalisation,
        weight = Weight(
            imperial = this.weightEntity.imperial,
            metric = this.weightEntity.metric
        ),
        wikipediaUrl = this.wikipediaUrl
    )
}


fun CatBreed.mapFromDomainModel(): CatBreedEntity {
    return CatBreedEntity(
        id = null,
        catBreedId = this.catBreedId,
        adaptability = this.adaptability,
        affectionLevel = this.affectionLevel,
        altNames = this.altNames,
        cfaUrl = this.cfaUrl,
        childFriendly = this.childFriendly,
        countryCode = this.countryCode,
        countryCodes = this.countryCodes,
        description = this.description,
        dogFriendly = this.dogFriendly,
        energyLevel = this.energyLevel,
        experimental = this.experimental,
        grooming = this.grooming,
        hairless = this.hairless,
        healthIssues = this.healthIssues,
        hypoallergenic = this.hypoallergenic,
        imageEntity = ImageEntity(
            id = this.image.id,
            url = this.image.url,
            width = this.image.width,
            height = this.image.height,
        ),
        indoor = this.indoor,
        intelligence = this.intelligence,
        lap = this.lap,
        lifeSpan = this.lifeSpan,
        name = this.name,
        natural = this.natural,
        origin = this.origin,
        rare = this.rare,
        referenceImageId = this.referenceImageId,
        rex = this.rex,
        sheddingLevel = this.sheddingLevel,
        shortLegs = this.shortLegs,
        socialNeeds = this.socialNeeds,
        strangerFriendly = this.strangerFriendly,
        suppressedTail = this.suppressedTail,
        temperament = this.temperament,
        vcahospitalsUrl = this.vcahospitalsUrl,
        vetstreetUrl = this.vetstreetUrl,
        vocalisation = this.vocalisation,
        weightEntity = WeightEntity(
            imperial = this.weight.imperial,
            metric = this.weight.metric
        ),
        wikipediaUrl = this.wikipediaUrl
    )
}

fun List<CatBreedEntity>.mapToDomainModelList(): List<CatBreed> {
    return this.mapToDomainModelList()
}

fun List<CatBreed>.mapFromDomainModelList(): List<CatBreedEntity> {
    return this.mapFromDomainModelList()
}