package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao.SavedItemDao
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao.SubscribedKeywordDao
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.dao.TagDao
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SavedItem
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SubscribedKeyword
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Tag
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [SavedItem::class, Tag::class, SubscribedKeyword::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun savedItemDao(): SavedItemDao
    abstract fun subscribedKeywordDao(): SubscribedKeywordDao
    abstract fun tagDao(): TagDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "newspaper.db"

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}