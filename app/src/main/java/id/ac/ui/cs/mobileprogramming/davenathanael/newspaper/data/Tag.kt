package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey val name: String
)