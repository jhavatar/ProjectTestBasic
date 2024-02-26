package io.chthonic.projecttestbasic.presentation.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import io.chthonic.projecttestbasic.databinding.ImageFragmentBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageFragment : Fragment() {
    private val viewModel: ImageViewModel by viewModels()

    private lateinit var binding: ImageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ImageFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imageUrlToShow.collect { imageUrl ->
                    if (imageUrl != null) {
                        binding.imageview.load(imageUrl)
                    }
                }
            }
        }
    }
}