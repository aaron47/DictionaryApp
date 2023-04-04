package com.aaron.dictionary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aaron.dictionary.feature_dictionary.data.local.entity.WordDefEntity

@Dao
interface WordDefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordDefs(defs: List<WordDefEntity>)

    @Query("DELETE FROM worddefentity WHERE word IN(:words)")
    suspend fun deleteWordDefs(words: List<String>)

    @Query("SELECT * FROM worddefentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordDefs(word: String): List<WordDefEntity>
}