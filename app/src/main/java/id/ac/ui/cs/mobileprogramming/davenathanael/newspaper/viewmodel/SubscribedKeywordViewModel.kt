package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SubscribedKeyword
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository.SubscribedKeywordRepository
import kotlinx.coroutines.launch

class SubscribedKeywordViewModel(private val repo: SubscribedKeywordRepository) : ViewModel() {
    val keywords = repo.getAll()

    fun insert(keyword: SubscribedKeyword) = viewModelScope.launch {
        repo.create(keyword)
    }
}

class SubscribedKeywordViewModelFactory(private val repo: SubscribedKeywordRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscribedKeywordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SubscribedKeywordViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}