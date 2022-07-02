package io.chthonic.projecttestbasic.presentation.image

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    savedState: SavedStateHandle
) : ViewModel() {

    companion object {
        const val IMAGE_URL_KEY = "imageUrl"
    }

    private val _imageUrlToShow = MutableStateFlow(savedState.get<String>(IMAGE_URL_KEY))

    val imageUrlToShow: StateFlow<String?>
        get() = _imageUrlToShow.asStateFlow()
}