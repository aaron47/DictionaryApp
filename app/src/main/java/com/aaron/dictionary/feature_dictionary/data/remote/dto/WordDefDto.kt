package com.aaron.dictionary.feature_dictionary.data.remote.dto

import com.aaron.dictionary.feature_dictionary.data.local.entity.WordDefEntity

data class WordDefDto(
    val license: License,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
)

fun WordDefDto.toWordDefEntity(): WordDefEntity {
    return WordDefEntity(
        meanings = meanings.map { it.toMeaning() },
        phonetic = phonetic,
        sourceUrls = sourceUrls.first(),
        word = word
    )
}