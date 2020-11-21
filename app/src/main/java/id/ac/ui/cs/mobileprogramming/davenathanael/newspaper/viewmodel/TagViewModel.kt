package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Tag
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository.TagRepository
import kotlinx.coroutines.launch

class TagViewModel(private val repo: TagRepository) : ViewModel() {
    val tags = repo.getAll()

    fun insert(tag: Tag) = viewModelScope.launch {
        repo.create(tag)
    }
}

class TagViewModelFactory(private val repo: TagRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TagViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TagViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}