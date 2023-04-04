package com.aaron.dictionary.feature_dictionary.data.remote

import com.aaron.dictionary.feature_dictionary.data.remote.dto.WordDefDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun getWordDefinition(@Path("word") word: String): List<WordDefDto>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}