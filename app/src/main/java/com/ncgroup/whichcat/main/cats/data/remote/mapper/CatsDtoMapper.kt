package com.ncgroup.whichcat.main.cats.data.remote.mapper


import com.ncgroup.whichcat.main.cats.domain.model.Weight
import com.ncgroup.whichcat.main.cats.data.remote.model.CatsDto
import com.ncgroup.whichcat.main.cats.domain.model.Breed
import com.ncgroup.whichcat.main.cats.domain.model.Cats


fun CatsDto.mapToDomainModel(): Cats {
    return Cats(
        id = null,
        breedId = this.id.orEmpty(),
        breeds = this.breedsDto.map { breedsDto ->
            Breed(
                id = breedsDto.id.orEmpty(),
                adaptability = breedsDto.adaptability ?: 0,
                affectionLevel = breedsDto.affectionLevel ?: 0,
                altNames = breedsDto.altNames.orEmpty(),
                cfaUrl = breedsDto.cfaUrl.orEmpty(),
                childFriendly = breedsDto.childFriendly ?: 0,
                countryCode = breedsDto.countryCode.orEmpty(),
                countryCodes = breedsDto.countryCodes.orEmpty(),
                description = breedsDto.description.orEmpty(),
                dogFriendly = breedsDto.dogFriendly ?: 0,
                energyLevel = breedsDto.energyLevel ?: 0,
                experimental = breedsDto.experimental ?: 0,
                grooming = breedsDto.grooming ?: 0,
                hairless = breedsDto.hairless ?: 0,
                healthIssues = breedsDto.healthIssues ?: 0,
                hypoallergenic = breedsDto.hypoallergenic ?: 0,
                indoor = breedsDto.indoor ?: 0,
                intelligence = breedsDto.intelligence ?: 0,
                lap = breedsDto.lap ?: 0,
                lifeSpan = breedsDto.lifeSpan.orEmpty(),
                name = breedsDto.name.orEmpty(),
                natural = breedsDto.natural ?: 0,
                origin = breedsDto.origin.orEmpty(),
                rare = breedsDto.rare ?: 0,
                referenceImageId = breedsDto.referenceImageId.orEmpty(),
                rex = breedsDto.rex ?: 0,
                sheddingLevel = breedsDto.sheddingLevel ?: 0,
                shortLegs = breedsDto.shortLegs ?: 0,
                socialNeeds = breedsDto.socialNeeds ?: 0,
                strangerFriendly = breedsDto.strangerFriendly ?: 0,
                suppressedTail = breedsDto.suppressedTail ?: 0,
                temperament = breedsDto.temperament.orEmpty(),
                vcahospitalsUrl = breedsDto.vcahospitalsUrl.orEmpty(),
                vetstreetUrl = breedsDto.vetstreetUrl.orEmpty(),
                vocalisation = breedsDto.vocalisation ?: 0,
                weight = Weight(
                    imperial = breedsDto.weightDto?.imperial.orEmpty(),
                    metric = breedsDto.weightDto?.metric.orEmpty()
                ),
                wikipediaUrl = breedsDto.wikipediaUrl.orEmpty()
            )
        },
        url = this.url.orEmpty(),
        width = this.width ?: 0,
        height = this.height ?: 0
    )
}

fun List<CatsDto>.mapToDomainModelList(): List<Cats> {
    return this.mapToDomainModelList()
}