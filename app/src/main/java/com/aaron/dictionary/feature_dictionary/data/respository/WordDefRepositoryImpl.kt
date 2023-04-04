package com.aaron.dictionary.feature_dictionary.data.respository

import com.aaron.dictionary.core.util.Resource
import com.aaron.dictionary.feature_dictionary.data.local.WordDefDao
import com.aaron.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.aaron.dictionary.feature_dictionary.data.remote.dto.toWordDefEntity
import com.aaron.dictionary.feature_dictionary.domain.model.WordDef
import com.aaron.dictionary.feature_dictionary.domain.repository.WordDefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordDefRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordDefDao
) : WordDefRepository {
    override fun getWordDef(word: String): Flow<Resource<List<WordDef>>> = flow {
        emit(Resource.Loading())
        val wordDefs = dao.getWordDefs(word).map { it.toWordDef() }
        emit(Resource.Loading(data = wordDefs))

        try {
            val remoteWordDefs = api.getWordDefinition(word)
            dao.deleteWordDefs(remoteWordDefs.map { it.word })
            dao.insertWordDefs(remoteWordDefs.map { it.toWordDefEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred", data = wordDefs))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection.", data = wordDefs))
        }

        val newWordDefs = dao.getWordDefs(word).map { it.toWordDef() }
        emit(Resource.Success(newWordDefs))
    }
}