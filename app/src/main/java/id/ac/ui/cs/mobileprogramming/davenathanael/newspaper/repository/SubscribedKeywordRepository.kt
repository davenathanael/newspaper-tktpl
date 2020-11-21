package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.repository

import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao.SubscribedKeywordDao
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SubscribedKeyword

class SubscribedKeywordRepository(private val subscribedKeywordDao: SubscribedKeywordDao) {
    fun getAll() = subscribedKeywordDao.loadAll()

    suspend fun create(keyword: SubscribedKeyword) = subscribedKeywordDao.insert(keyword)
}