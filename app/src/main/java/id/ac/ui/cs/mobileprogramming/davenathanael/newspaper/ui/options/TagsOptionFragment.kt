package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.ui.options

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.material.textfield.TextInputLayout
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.NewspaperApplication
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.adapter.TagListAdapter
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.Tag
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.FragmentOptionsTagsBinding
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.TagViewModel
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.TagViewModelFactory

class TagsOptionFragment : Fragment() {
    private lateinit var binding: FragmentOptionsTagsBinding

    private val viewModel: TagViewModel by viewModels {
        TagViewModelFactory((activity?.application as NewspaperApplication).tagRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsTagsBinding.inflate(inflater, container, false)

        binding.setClickListener {
            val input = binding.root.findViewById<TextInputLayout>(R.id.card_tags_filledTextField)
            val value = input.editText!!.text.toString()
            viewModel.insert(Tag(value))
            input.editText!!.text.clear()
        }

        val adapter = TagListAdapter()
        binding.tagList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: TagListAdapter) {
        viewModel.tags.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }
    }
}