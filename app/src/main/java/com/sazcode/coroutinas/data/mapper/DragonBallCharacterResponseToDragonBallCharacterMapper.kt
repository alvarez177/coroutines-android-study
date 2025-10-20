package com.sazcode.coroutinas.data.mapper

import com.sazcode.coroutinas.data.response.dragonballz.DragonBallCharacterResponse
import com.sazcode.coroutinas.domain.model.DragonBallCharacter
import com.sazcode.coroutinas.shared.Transform
import jakarta.inject.Inject

class DragonBallCharacterResponseToDragonBallCharacterMapper @Inject constructor() :
    Transform<DragonBallCharacterResponse, DragonBallCharacter>() {

    override fun transform(type1: DragonBallCharacterResponse): DragonBallCharacter {
        return DragonBallCharacter(
            id = type1.id.orEmpty(),
            name = type1.name.orEmpty(),
            ki = type1.ki.orEmpty(),
            race = type1.race.orEmpty(),
            maxKi = type1.maxKi.orEmpty(),
            gender = type1.gender.orEmpty(),
            description = type1.description.orEmpty(),
            image = type1.image.orEmpty(),
            affiliation = type1.affiliation.orEmpty()
        )
    }
}