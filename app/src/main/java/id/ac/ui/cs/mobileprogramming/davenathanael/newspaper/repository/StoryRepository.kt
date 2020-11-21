package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository

import androidx.lifecycle.liveData
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Resource
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Story
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.StoryDataSource

class StoryRepository(private val source: StoryDataSource) {
    fun getAll() = liveData<List<Story>> {
        val topStoriesResponse = source.getTopStories()
        if (topStoriesResponse.status == Resource.Status.ERROR) {
            emit(emptyList())
            return@liveData
        }
        val result = arrayListOf<Story>()
        topStoriesResponse.data!!.take(10).forEach { id ->
            val storyResponse = source.getStoryWithId(id)

            if (storyResponse.status == Resource.Status.SUCCESS) {
                result.add(storyResponse.data!!)
            }
        }

        emit(result)
    }
}