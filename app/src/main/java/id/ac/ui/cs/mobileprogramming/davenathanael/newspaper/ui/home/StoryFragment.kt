package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.R

class StoryFragment : Fragment() {

    private val args: StoryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_story, container, false)

        root.findViewById<WebView>(R.id.webview).apply {
            settings.apply {
                loadsImagesAutomatically = true
                javaScriptEnabled = true
                domStorageEnabled = true
            }
            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webViewClient = WebViewClient()
            loadUrl(args.url)
        }

        root.findViewById<MaterialToolbar>(R.id.topAppBar).apply {
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.save_item_button -> {
                        val newArgs = bundleOf(
                            Pair("url", args.url),
                            Pair("title", args.title),
                            Pair("author", args.author),
                            Pair("id", args.id)
                        )
                        root.findNavController().navigate(R.id.navigation_new_saved_item, newArgs)
                        true
                    }
                    else -> false
                }
            }
        }

        return root
    }
}