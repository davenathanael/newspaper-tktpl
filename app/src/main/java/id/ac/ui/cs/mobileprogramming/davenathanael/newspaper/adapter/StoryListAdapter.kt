package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Story
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.ListItemStoryBinding
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.StoryViewModel

class StoryListAdapter(private val viewModel: StoryViewModel) :
    ListAdapter<Story, StoryListAdapter.StoryViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_story,
                parent,
                false
            ),
            viewModel
        )
    }

    override fun onBindViewHolder(viewHolder: StoryViewHolder, position: Int) {
        val current = getItem(position)
        viewHolder.bind(current)
    }

    class StoryViewHolder(
        private val binding: ListItemStoryBinding,
        private val viewModel: StoryViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        private val titleView = itemView.findViewById<TextView>(R.id.list_item_story_title)
        private val authorView = itemView.findViewById<TextView>(R.id.list_item_story_author)
        private val commentsView = itemView.findViewById<TextView>(R.id.list_item_story_comments)

        fun bind(story: Story) {
            titleView.text = story.title
            commentsView.text = story.descendants.toString()
            val author = story.by
            authorView.text = "by $author"
            binding.setClickListener {
                viewModel.selectedStory.value = story
                navigate(binding.root, story)
            }
        }

        private fun navigate(view: View, story: Story) {
            val args = bundleOf(
                Pair("url", story.url),
                Pair("title", story.title),
                Pair("author", story.by),
                Pair("id", story.id)
            )
            view.findNavController().navigate(R.id.navigation_story, args)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}