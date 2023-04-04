package com.aaron.dictionary.feature_dictionary.presentation

import com.aaron.dictionary.feature_dictionary.domain.model.WordDef

data class WordDefState(
    val wordDefItems: List<WordDef> = emptyList(),
    val isLoading: Boolean = false,
)
