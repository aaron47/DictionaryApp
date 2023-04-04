package com.aaron.dictionary.feature_dictionary.data.remote.dto

import com.aaron.dictionary.feature_dictionary.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<Any>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
)

fun MeaningDto.toMeaning(): Meaning {
    return Meaning(
        antonyms = antonyms,
        definitions = definitions.map { it.toDefinition() },
        partOfSpeech = partOfSpeech,
        synonyms = synonyms
    )
}