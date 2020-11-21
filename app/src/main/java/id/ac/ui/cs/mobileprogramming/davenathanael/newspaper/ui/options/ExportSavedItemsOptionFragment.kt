package id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.ui.options

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.NewspaperApplication
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.data.SavedItem
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.databinding.FragmentOptionsExportSavedItemsBinding
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.SavedItemViewModel
import id.ac.ui.cs.mobileprogramming.davenathanael.newspaper.viewmodel.SavedItemViewModelFactory
import java.io.File
import java.io.FileOutputStream

class ExportSavedItemsOptionFragment : Fragment() {
    private lateinit var binding: FragmentOptionsExportSavedItemsBinding

    private val viewModel: SavedItemViewModel by viewModels {
        SavedItemViewModelFactory((activity?.application as NewspaperApplication).savedItemRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionsExportSavedItemsBinding.inflate(inflater, container, false)

        viewModel.savedItems.observe(viewLifecycleOwner) { items ->
            binding.setClickListener {
                val fileName = "export-saved-items.csv"
                writeFile(fileName, constructContent(items))
                Snackbar.make(
                    binding.root,
                    "Saved items exported to storage!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

    private fun constructContent(items: List<SavedItem>): String {
        val header = "id,title,author,url,tag,storyId\n"
        return header + items.map { "${it.id},${it.title},${it.author},${it.url},${it.tag},${it.storyId}" }
            .joinToString("\n")
    }

    private fun writeFile(fileName: String, content: String) {
        val state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED != state) return

        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            fileName
        )
        var outStream: FileOutputStream?

        try {
            file.createNewFile()
            outStream = FileOutputStream(file, true)
            outStream.write(content.toByteArray())
            outStream.flush()
            outStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}