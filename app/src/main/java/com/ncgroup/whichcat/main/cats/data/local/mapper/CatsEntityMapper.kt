package com.ncgroup.whichcat.main.cats.data.local.mapper

import com.ncgroup.whichcat.main.cats.data.local.model.CatsEntity
import com.ncgroup.whichcat.main.cats.data.local.model.WeightEntity
import com.ncgroup.whichcat.main.cats.domain.model.Cats
import com.ncgroup.whichcat.main.cats.data.local.model.BreedEntity
import com.ncgroup.whichcat.main.cats.domain.model.Breed


fun CatsEntity.mapToDomainModel(): Cats {
    return Cats(
        id = this.id,
        breedId = this.breedId,
        breeds = this.breedsEntity.map { breedsDto ->
            Breed(
                id = breedsDto.id,
                adaptability = breedsDto.adaptability,
                affectionLevel = breedsDto.affectionLevel,
                altNames = breedsDto.altNames,
                cfaUrl = breedsDto.cfaUrl,
                childFriendly = breedsDto.childFriendly,
                countryCode = breedsDto.countryCode,
                countryCodes = breedsDto.countryCodes,
                description = breedsDto.description,
                dogFriendly = breedsDto.dogFriendly,
                energyLevel = breedsDto.energyLevel,
                experimental = breedsDto.experimental,
                grooming = breedsDto.grooming,
                hairless = breedsDto.hairless,
                healthIssues = breedsDto.healthIssues,
                hypoallergenic = breedsDto.hypoallergenic,
                indoor = breedsDto.indoor,
                intelligence = breedsDto.intelligence,
                lap = breedsDto.lap,
                lifeSpan = breedsDto.lifeSpan,
                name = breedsDto.name,
                natural = breedsDto.natural,
                origin = breedsDto.origin,
                rare = breedsDto.rare,
                referenceImageId = breedsDto.referenceImageId,
                rex = breedsDto.rex,
                sheddingLevel = breedsDto.sheddingLevel,
                shortLegs = breedsDto.shortLegs,
                socialNeeds = breedsDto.socialNeeds,
                strangerFriendly = breedsDto.strangerFriendly,
                suppressedTail = breedsDto.suppressedTail,
                temperament = breedsDto.temperament,
                vcahospitalsUrl = breedsDto.vcahospitalsUrl,
                vetstreetUrl = breedsDto.vetstreetUrl,
                vocalisation = breedsDto.vocalisation,
                weight = com.ncgroup.whichcat.main.cats.domain.model.Weight(
                    imperial = breedsDto.weightEntity.imperial,
                    metric = breedsDto.weightEntity.metric
                ),
                wikipediaUrl = breedsDto.wikipediaUrl,
            )
        },
        url = this.url,
        width = this.width,
        height = this.height
    )
}

fun Cats.mapFromDomainModel(): CatsEntity {
    return CatsEntity(
        id = this.id,
        breedId = this.breedId,
        breedsEntity = this.breeds.map { breedsEntity ->
            BreedEntity(
                id = breedsEntity.id,
                adaptability = breedsEntity.adaptability,
                affectionLevel = breedsEntity.affectionLevel,
                altNames = breedsEntity.altNames,
                cfaUrl = breedsEntity.cfaUrl,
                childFriendly = breedsEntity.childFriendly,
                countryCode = breedsEntity.countryCode,
                countryCodes = breedsEntity.countryCodes,
                description = breedsEntity.description,
                dogFriendly = breedsEntity.dogFriendly,
                energyLevel = breedsEntity.energyLevel,
                experimental = breedsEntity.experimental,
                grooming = breedsEntity.grooming,
                hairless = breedsEntity.hairless,
                healthIssues = breedsEntity.healthIssues,
                hypoallergenic = breedsEntity.hypoallergenic,
                indoor = breedsEntity.indoor,
                intelligence = breedsEntity.intelligence,
                lap = breedsEntity.lap,
                lifeSpan = breedsEntity.lifeSpan,
                name = breedsEntity.name,
                natural = breedsEntity.natural,
                origin = breedsEntity.origin,
                rare = breedsEntity.rare,
                referenceImageId = breedsEntity.referenceImageId,
                rex = breedsEntity.rex,
                sheddingLevel = breedsEntity.sheddingLevel,
                shortLegs = breedsEntity.shortLegs,
                socialNeeds = breedsEntity.socialNeeds,
                strangerFriendly = breedsEntity.strangerFriendly,
                suppressedTail = breedsEntity.suppressedTail,
                temperament = breedsEntity.temperament,
                vcahospitalsUrl = breedsEntity.vcahospitalsUrl,
                vetstreetUrl = breedsEntity.vetstreetUrl,
                vocalisation = breedsEntity.vocalisation,
                weightEntity = WeightEntity(
                    imperial = breedsEntity.weight.imperial,
                    metric = breedsEntity.weight.metric
                ),
                wikipediaUrl = breedsEntity.wikipediaUrl,
            )
        },
        url = this.url,
        width = this.width,
        height = this.height
    )
}

fun List<CatsEntity>.mapToDomainModelList(): List<Cats> {
    return this.mapToDomainModelList()
}

fun List<Cats>.mapFromDomainModelList(): List<CatsEntity> {
    return this.mapFromDomainModelList()
}