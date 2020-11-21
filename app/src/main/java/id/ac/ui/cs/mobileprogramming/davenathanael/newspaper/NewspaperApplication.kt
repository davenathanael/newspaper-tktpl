package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper

import android.app.Application
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.api.HackerNewsService
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Story
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.StoryDataSource
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.database.AppDatabase
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository.SavedItemRepository
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository.StoryRepository
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository.SubscribedKeywordRepository
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository.TagRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NewspaperApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val savedItemRepository by lazy { SavedItemRepository(database.savedItemDao()) }
    val tagRepository by lazy { TagRepository(database.tagDao()) }
    val subscribedKeywordRepository by lazy { SubscribedKeywordRepository(database.subscribedKeywordDao()) }
    val storyRepository by lazy { StoryRepository(StoryDataSource(HackerNewsService.create())) }
}