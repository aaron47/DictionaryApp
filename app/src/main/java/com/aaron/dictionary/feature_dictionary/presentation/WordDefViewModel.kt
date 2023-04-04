package com.aaron.dictionary.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aaron.dictionary.core.util.Resource
import com.aaron.dictionary.feature_dictionary.domain.use_case.GetWordDefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDefViewModel @Inject constructor(
    private val getWordDefUseCase: GetWordDefUseCase,
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordDefState())
    val state: State<WordDefState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordDefUseCase(query).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            wordDefItems = result.data ?: emptyList(),
                            isLoading = false,
                        )
                    }

                    is Resource.Error -> {
                       _state.value = _state.value.copy(
                            wordDefItems = result.data ?: emptyList(),
                            isLoading = false,
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "An unexpected error occurred"))
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            wordDefItems = result.data ?: emptyList(),
                            isLoading = true,
                        )
                    }
                }
            }.launchIn(this)
        }
    }


    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}