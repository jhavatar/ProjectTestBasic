package io.chthonic.projecttestbasic.presentation.image

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor() : ViewModel() {

    private val _imageUrlToShow = MutableStateFlow<String?>(null)
    val imageUrlToShow: StateFlow<String?>
        get() = _imageUrlToShow

    fun onCreate(imageUrlArgument: String?) {
        _imageUrlToShow.value = imageUrlArgument
    }
}