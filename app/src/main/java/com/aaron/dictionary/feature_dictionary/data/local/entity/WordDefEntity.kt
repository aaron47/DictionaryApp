package com.aaron.dictionary.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aaron.dictionary.feature_dictionary.domain.model.Meaning
import com.aaron.dictionary.feature_dictionary.domain.model.WordDef

@Entity
data class WordDefEntity(
    @PrimaryKey val id: Int? = null,
    val word: String,
    val meanings: List<Meaning>,
    val sourceUrls: String,
    val phonetic: String,
) {
    fun toWordDef(): WordDef {
        return WordDef(
            word = word,
            meanings = meanings,
            sourceUrls = sourceUrls,
            phonetic = phonetic,
        )
    }
}
