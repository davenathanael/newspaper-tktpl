package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.ui.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.material.textfield.TextInputLayout
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.NewspaperApplication
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.adapter.SubscribedKeywordListAdapter
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SubscribedKeyword
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.FragmentOptionsSubscribedKeywordsBinding
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.SubscribedKeywordViewModel
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.SubscribedKeywordViewModelFactory

class SubscribedKeywordsOptionFragment : Fragment() {
    private lateinit var binding: FragmentOptionsSubscribedKeywordsBinding

    private val viewModel: SubscribedKeywordViewModel by viewModels {
        SubscribedKeywordViewModelFactory((activity?.application as NewspaperApplication).subscribedKeywordRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsSubscribedKeywordsBinding.inflate(inflater, container, false)

        binding.setClickListener {
            val input =
                binding.root.findViewById<TextInputLayout>(R.id.card_subscribed_keywords_filledTextField)
            val value = input.editText!!.text.toString()
            viewModel.insert(SubscribedKeyword(value))
            input.editText!!.text.clear()
        }

        val adapter = SubscribedKeywordListAdapter()
        binding.subscribedKeywordList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: SubscribedKeywordListAdapter) {
        viewModel.keywords.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }
    }
}