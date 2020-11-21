package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data

import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.api.HackerNewsService

class StoryDataSource(private val service: HackerNewsService) : BaseDataSource() {
    suspend fun getTopStories() = getResult { service.getTopStories() }
    suspend fun getStoryWithId(id: Long) = getResult { service.getStoryItem(id) }
}
