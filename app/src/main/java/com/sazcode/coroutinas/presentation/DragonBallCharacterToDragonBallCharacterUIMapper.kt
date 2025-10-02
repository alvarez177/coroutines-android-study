package com.sazcode.coroutinas.presentation

import com.sazcode.coroutinas.domain.DragonBallCharacter
import com.sazcode.coroutinas.presentation.model.DragonBallCharacterUI
import com.sazcode.coroutinas.shared.Transform
import jakarta.inject.Inject

class DragonBallCharacterToDragonBallCharacterUIMapper @Inject constructor() : Transform<DragonBallCharacter, DragonBallCharacterUI>() {

    override fun transform(type1: DragonBallCharacter): DragonBallCharacterUI {
        return DragonBallCharacterUI(
            id = type1.id,
            name = type1.name,
            ki = type1.ki,
            race = type1.race,
            maxKi = type1.maxKi,
            gender = type1.gender,
            description = type1.description,
            image = type1.image,
            affiliation = type1.affiliation
        )
    }
}