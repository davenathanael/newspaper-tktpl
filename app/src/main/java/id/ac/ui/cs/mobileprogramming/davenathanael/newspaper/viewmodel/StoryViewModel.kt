package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Story
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository.StoryRepository

class StoryViewModel(repository: StoryRepository) : ViewModel() {
    val stories = repository.getAll()
    val selectedStory: MutableLiveData<Story> by lazy { MutableLiveData<Story>() }
}

class StoryViewModelFactory(private val storyRepository: StoryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StoryViewModel(storyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}