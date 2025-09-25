package com.sazcode.coroutinas.data

import com.sazcode.coroutinas.domain.DragonBallCharacter
import com.sazcode.coroutinas.shared.Transform
import jakarta.inject.Inject

class DragonBallCharacterResponseToDragonBallCharacterMapper @Inject constructor() :
    Transform<DragonBallCharacterResponse, DragonBallCharacter>() {

    override fun transform(type1: DragonBallCharacterResponse): DragonBallCharacter {
        return DragonBallCharacter(
            id = type1.id,
            name = type1.name,
            ki = type1.ki,
            race = type1.race,
            maxKi = type1.maxKi,
            gender = type1.gender,
            description =  type1.description,
            image = type1.image,
            affiliation = type1.affiliation
        )
    }
}