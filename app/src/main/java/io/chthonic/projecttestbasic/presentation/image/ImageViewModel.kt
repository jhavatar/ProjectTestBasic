package io.chthonic.projecttestbasic.presentation.image

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor() : ViewModel() {

    var _imageUrlToShow = MutableStateFlow<String?>(null)
    val imageUrlToShow: StateFlow<String?>
        get() = _imageUrlToShow

    fun onCreate(arguments: Bundle?) {
        _imageUrlToShow.value = arguments?.getString("imageUrl", null)
    }
}