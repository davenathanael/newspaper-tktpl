package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.ui.saveditems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.NewspaperApplication
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SavedItem
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.FragmentNewSavedItemBinding
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.SavedItemViewModel
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.SavedItemViewModelFactory
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.TagViewModel
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.TagViewModelFactory

class NewSavedItemFragment : Fragment() {

    private val args: NewSavedItemFragmentArgs by navArgs()
    private lateinit var binding: FragmentNewSavedItemBinding
    private val viewModel: SavedItemViewModel by viewModels {
        SavedItemViewModelFactory((activity?.application as NewspaperApplication).savedItemRepository)
    }
    private val tagViewModel: TagViewModel by viewModels {
        TagViewModelFactory((activity?.application as NewspaperApplication).tagRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewSavedItemBinding.inflate(inflater, container, false)
        binding.saveItemTitleText.text = args.title
        tagViewModel.tags.observe(viewLifecycleOwner) {
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                it.map { t -> t.name }
            )
            binding.saveItemTagSelect.adapter = adapter

        }

        binding.saveItemSubmit.setOnClickListener {
            val tag = binding.saveItemTagSelect.selectedItem as String
            val newSavedItem =
                SavedItem(
                    title = args.title,
                    author = args.author,
                    url = args.url,
                    tag = tag,
                    storyId = args.id
                )
            viewModel.insert(newSavedItem)
            Snackbar.make(binding.root, "Story has been saved!", Snackbar.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }
        binding.root.findViewById<MaterialToolbar>(R.id.topAppBar_save_item)
            .setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        return binding.root
    }
}