package com.sazcode.coroutinas.presentation.mapper

import com.sazcode.coroutinas.domain.model.RickAndMortyCharacter
import com.sazcode.coroutinas.presentation.model.ComeFromType
import com.sazcode.coroutinas.presentation.model.MixedContentUI
import com.sazcode.coroutinas.shared.Transform
import jakarta.inject.Inject

class RickAndMortyCharacterToRickAndMortyCharacterUIMapper @Inject constructor() :
    Transform<RickAndMortyCharacter, MixedContentUI>() {

    override fun transform(type1: RickAndMortyCharacter): MixedContentUI {
        return MixedContentUI(
            id = type1.id,
            image = type1.image,
            title = type1.name,
            subTitle = type1.species,
            comeFromType = ComeFromType.RICK_AND_MORTY
        )
    }
}