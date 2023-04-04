package com.aaron.dictionary.feature_dictionary.domain.model

data class WordDef(
    val word: String,
    val meanings: List<Meaning>,
    val sourceUrls: String,
    val phonetic: String,
)