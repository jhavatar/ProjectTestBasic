package io.chthonic.projecttestbasic.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import io.chthonic.projecttestbasic.databinding.MainFragmentBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.onImageButtonClicked()
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.navigate.collect { navTarget ->
                    when (navTarget) {
                        is MainViewModel.NavigationTarget.ImageScreen -> {
                            val action = MainFragmentDirections.actionMainFragmentToImageFragment()
                                .setImageUrl(navTarget.url)
                            NavHostFragment.findNavController(this@MainFragment)
                                .navigate(action)
                        }

                        else -> {}
                    }
                    viewModel.onNavigationObserved()
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadingIsVisible.collect { isLoadingVisible ->
                    binding.loadingView.visibility =
                        if (isLoadingVisible) View.VISIBLE else View.GONE
                    binding.imageButton.visibility =
                        if (isLoadingVisible) View.GONE else View.VISIBLE
                }
            }
        }
    }
}