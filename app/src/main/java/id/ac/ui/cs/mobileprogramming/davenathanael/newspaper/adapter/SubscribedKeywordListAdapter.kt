package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SubscribedKeyword
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.ListItemSubscribedKeywordBinding

class SubscribedKeywordListAdapter :
    ListAdapter<SubscribedKeyword, SubscribedKeywordListAdapter.SubscribedKeywordViewHolder>(
        COMPARATOR
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribedKeywordViewHolder {
        return SubscribedKeywordViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_subscribed_keyword,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: SubscribedKeywordViewHolder, position: Int) {
        val current = getItem(position)
        viewHolder.bind(current.word)
    }

    class SubscribedKeywordViewHolder(
        binding: ListItemSubscribedKeywordBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val keywordNameView =
            itemView.findViewById<TextView>(R.id.list_item_subscribed_keyword_name)

        fun bind(keyword: String) {
            keywordNameView.text = keyword
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<SubscribedKeyword>() {
            override fun areItemsTheSame(
                oldItem: SubscribedKeyword,
                newItem: SubscribedKeyword
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: SubscribedKeyword,
                newItem: SubscribedKeyword
            ): Boolean {
                return oldItem.word == newItem.word
            }
        }
    }
}