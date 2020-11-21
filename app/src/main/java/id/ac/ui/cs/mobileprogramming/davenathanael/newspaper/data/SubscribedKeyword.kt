package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscribed_keywords")
data class SubscribedKeyword(
    @PrimaryKey val word: String
)