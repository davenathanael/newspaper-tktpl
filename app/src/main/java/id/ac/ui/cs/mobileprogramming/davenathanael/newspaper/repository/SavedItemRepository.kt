package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository

import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao.SavedItemDao
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SavedItem

class SavedItemRepository(private val savedItemDao: SavedItemDao) {
    fun getAll() = savedItemDao.loadAll()

    suspend fun create(item: SavedItem) = savedItemDao.insert(item)
}