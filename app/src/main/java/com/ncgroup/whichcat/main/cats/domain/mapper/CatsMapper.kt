package com.ncgroup.whichcat.main.cats.domain.mapper

import com.ncgroup.whichcat.main.cats.domain.model.Breed
import com.ncgroup.whichcat.main.cats.domain.model.BreedCatDetail
import com.ncgroup.whichcat.main.cats.domain.model.WeightCatDetail


fun Breed.mapToBreedCatDetail(): BreedCatDetail {
   return BreedCatDetail(
       id = this.id,
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
       weight = WeightCatDetail(
           imperial = this.weight.imperial,
           metric = this.weight.metric
       ),
       wikipediaUrl = this.wikipediaUrl
   )
}