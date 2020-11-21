package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository

import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao.TagDao
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Tag

class TagRepository(private val tagDao: TagDao) {
    fun getAll() = tagDao.loadAll()

    suspend fun create(tag: Tag) = tagDao.insert(tag)
}