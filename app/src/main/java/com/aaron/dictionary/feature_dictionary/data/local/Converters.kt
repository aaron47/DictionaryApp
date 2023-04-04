package com.aaron.dictionary.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.aaron.dictionary.feature_dictionary.data.util.JsonParser
import com.aaron.dictionary.feature_dictionary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser,
) {
    @TypeConverter
    fun fromDefJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toDefJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(meanings, object : TypeToken<ArrayList<Meaning>>(){}.type) ?: "[]"
    }
}