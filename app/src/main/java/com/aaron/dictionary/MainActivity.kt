package com.aaron.dictionary

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aaron.dictionary.feature_dictionary.presentation.WordDefItem
import com.aaron.dictionary.feature_dictionary.presentation.WordDefViewModel
import com.aaron.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val viewModel = hiltViewModel<WordDefViewModel>()
                    val state = viewModel.state.value
                    val scaffoldState = rememberScaffoldState()

                    LaunchedEffect(key1 = true) {
                        viewModel.eventFlow.collectLatest { event ->
                            when (event) {
                                is WordDefViewModel.UIEvent.ShowSnackBar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                                }
                            }
                        }
                    }

                    Scaffold(
                        scaffoldState = scaffoldState,
                    ) {
                        Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
                            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                                TextField(
                                    value = viewModel.searchQuery.value,
                                    onValueChange = { viewModel.onSearch(it) },
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = { Text(text = "Search") }
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                LazyColumn(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(state.wordDefItems.size) { index ->
                                        val wordDef = state.wordDefItems[index]

                                        if (index > 0) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }

                                        WordDefItem(wordDef = wordDef)

                                        if (index < state.wordDefItems.size - 1) {
                                            Divider()
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}