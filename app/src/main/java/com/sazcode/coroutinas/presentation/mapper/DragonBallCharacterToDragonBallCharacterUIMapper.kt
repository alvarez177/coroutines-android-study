package com.sazcode.coroutinas.presentation.mapper

import com.sazcode.coroutinas.domain.DragonBallCharacter
import com.sazcode.coroutinas.presentation.model.ComeFromType
import com.sazcode.coroutinas.presentation.model.DragonBallCharacterUI
import com.sazcode.coroutinas.presentation.model.MixedContentUI
import com.sazcode.coroutinas.shared.Transform
import jakarta.inject.Inject

class DragonBallCharacterToDragonBallCharacterUIMapper @Inject constructor() :
    Transform<DragonBallCharacter, MixedContentUI>() {

    override fun transform(type1: DragonBallCharacter): MixedContentUI {
        return MixedContentUI(
            id = type1.id,
            image = type1.image,
            title = type1.name,
            subTitle = type1.race,
            comeFromType = ComeFromType.DRAGON_BALL
        )
        /* return DragonBallCharacterUI(
            id = type1.id,
            name = type1.name,
            ki = type1.ki,
            race = type1.race,
            maxKi = type1.maxKi,
            gender = type1.gender,
            description = type1.description,
            image = type1.image,
            affiliation = type1.affiliation
        ) */
    }
}