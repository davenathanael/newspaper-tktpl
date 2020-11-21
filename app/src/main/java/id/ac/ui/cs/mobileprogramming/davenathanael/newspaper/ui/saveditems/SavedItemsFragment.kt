package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.ui.saveditems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.NewspaperApplication
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.adapter.SavedItemListAdapter
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.FragmentSavedItemsBinding
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.SavedItemViewModel
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.SavedItemViewModelFactory

class SavedItemsFragment : Fragment() {

    private lateinit var binding: FragmentSavedItemsBinding
    private val viewModel: SavedItemViewModel by viewModels {
        SavedItemViewModelFactory((activity?.application as NewspaperApplication).savedItemRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedItemsBinding.inflate(inflater, container, false)
        val adapter = SavedItemListAdapter()
        binding.savedItemList.adapter = adapter

        subscribeUi(adapter)
        return binding.root
    }

    private fun subscribeUi(adapter: SavedItemListAdapter) {
        viewModel.savedItems.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }
    }

}