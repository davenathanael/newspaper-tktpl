package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SavedItem
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository.SavedItemRepository
import kotlinx.coroutines.launch

class SavedItemViewModel(private val savedItemRepository: SavedItemRepository): ViewModel() {
    val savedItems = savedItemRepository.getAll()

    fun insert(savedItem: SavedItem) = viewModelScope.launch {
        savedItemRepository.create(savedItem)
    }
}

class SavedItemViewModelFactory(private val savedItemRepository: SavedItemRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SavedItemViewModel(savedItemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}