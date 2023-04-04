package com.aaron.dictionary.feature_dictionary.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aaron.dictionary.feature_dictionary.domain.model.WordDef

@Composable
fun WordDefItem(
    wordDef: WordDef,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = wordDef.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = isSystemInDarkTheme().let { if (it) Color.White else Color.Black }
        )

        Text(
            text = wordDef.phonetic,
            fontWeight = FontWeight.Light,
            fontSize = 16.sp,
            color = isSystemInDarkTheme().let { if (it) Color.White else Color.Black }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = wordDef.sourceUrls,
        )

        wordDef.meanings.forEach { meaning ->
            Text(
                text = meaning.partOfSpeech,
                fontWeight = FontWeight.Bold,
            )
            meaning.definitions.forEachIndexed { index, definition ->
                Text(
                    text = "${index + 1}. ${definition.definition}",
                    fontWeight = FontWeight.Light,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Example: ${definition.example}",
                    fontWeight = FontWeight.Light,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}