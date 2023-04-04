package com.aaron.dictionary.feature_dictionary.domain.use_case

import com.aaron.dictionary.core.util.Resource
import com.aaron.dictionary.feature_dictionary.domain.model.WordDef
import com.aaron.dictionary.feature_dictionary.domain.repository.WordDefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordDefUseCase(
    private val repository: WordDefRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordDef>>> {
        if (word.isBlank()) {
            return flow { }
        }
        return repository.getWordDef(word)
    }
}