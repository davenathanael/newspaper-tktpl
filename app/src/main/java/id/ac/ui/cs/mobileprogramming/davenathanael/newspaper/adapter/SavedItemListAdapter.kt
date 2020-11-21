package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.ListItemSavedItemBinding
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SavedItem

class SavedItemListAdapter :
    ListAdapter<SavedItem, SavedItemListAdapter.SavedItemViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedItemViewHolder {
        return SavedItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_saved_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: SavedItemViewHolder, position: Int) {
        val current = getItem(position)
        viewHolder.bind(current)
    }

    class SavedItemViewHolder(
        private val binding: ListItemSavedItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val titleView = itemView.findViewById<TextView>(R.id.list_item_saved_item_title)
        private val authorView = itemView.findViewById<TextView>(R.id.list_item_saved_item_author)
        fun bind(item: SavedItem) {
            titleView.text = item.title
            authorView.text = "by ${item.author}"
            binding.setClickListener {
                navigate(binding.root, item)
            }
        }

        private fun navigate(view: View, item: SavedItem) {
            val args = bundleOf(
                Pair("url", item.url),
                Pair("title", item.title),
                Pair("author", item.author),
                Pair("id", item.storyId),
                Pair("isSaved", true)
            )
            view.findNavController().navigate(R.id.navigation_story, args)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<SavedItem>() {
            override fun areItemsTheSame(oldItem: SavedItem, newItem: SavedItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SavedItem, newItem: SavedItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}