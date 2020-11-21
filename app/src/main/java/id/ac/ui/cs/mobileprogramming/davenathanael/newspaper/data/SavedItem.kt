package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_items")
data class SavedItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val url: String,
    val tag: String,
    val storyId: Long
)