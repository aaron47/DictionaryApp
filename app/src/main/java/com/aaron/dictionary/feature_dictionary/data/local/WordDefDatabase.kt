package com.aaron.dictionary.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aaron.dictionary.feature_dictionary.data.local.entity.WordDefEntity

@Database(
    entities = [WordDefEntity::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class WordDefDatabase : RoomDatabase() {

    abstract val dao: WordDefDao
}