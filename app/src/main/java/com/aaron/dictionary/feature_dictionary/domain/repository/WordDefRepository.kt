package com.aaron.dictionary.feature_dictionary.domain.repository

import com.aaron.dictionary.core.util.Resource
import com.aaron.dictionary.feature_dictionary.domain.model.WordDef
import kotlinx.coroutines.flow.Flow

interface WordDefRepository {

    fun getWordDef(word: String): Flow<Resource<List<WordDef>>>
}