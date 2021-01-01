package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.NewspaperApplication
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.adapter.StoryListAdapter
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.FragmentHomeBinding
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.StoryViewModel
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.StoryViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: StoryViewModel by viewModels {
        StoryViewModelFactory((activity?.application as NewspaperApplication).storyRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val adapter = StoryListAdapter(viewModel)
        binding.storyList.adapter = adapter

        subscribeUi(adapter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cm = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if (isConnected) {
            Snackbar.make(
                binding.root,
                "Connected to internet! Fetching latest stories...",
                Snackbar.LENGTH_SHORT
            ).show()
        } else {
            Snackbar.make(
                binding.root,
                "Can't fetch latest stories! Please make sure you're connected",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun subscribeUi(adapter: StoryListAdapter) {
        viewModel.stories.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }
    }
}