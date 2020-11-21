package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Tag
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.ListItemTagBinding

class TagListAdapter : ListAdapter<Tag, TagListAdapter.TagViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_tag,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: TagViewHolder, position: Int) {
        val current = getItem(position)
        viewHolder.bind(current.name)
    }

    class TagViewHolder(
        binding: ListItemTagBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val tagNameView = itemView.findViewById<TextView>(R.id.list_item_tag_name)
        fun bind(tagName: String) {
            tagNameView.text = tagName
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Tag>() {
            override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}