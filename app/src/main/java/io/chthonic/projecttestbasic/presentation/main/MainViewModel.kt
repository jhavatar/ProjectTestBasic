package io.chthonic.projecttestbasic.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.chthonic.projecttestbasic.domain.GetDogImageUsecase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDogImageUsecase: GetDogImageUsecase
) : ViewModel() {

    sealed class NavigationTarget {
        data class ImageScreen(val url: String) : NavigationTarget()
    }

    private val _loadingIsVisible = MutableStateFlow(false)
    val loadingIsVisible: StateFlow<Boolean>
        get() = _loadingIsVisible.asStateFlow()

    private val _navigate = MutableStateFlow<NavigationTarget?>(null)
    val navigate: StateFlow<NavigationTarget?>
        get() = _navigate.asStateFlow()

    fun onNavigationObserved() {
        _navigate.value = null
    }

    fun onImageButtonClicked() {
        viewModelScope.launch {
            try {
                _loadingIsVisible.value = true
                getDogImageUsecase.execute().let { dogImage ->
                    _navigate.value = NavigationTarget.ImageScreen(dogImage.url)
                    viewModelScope.launch {
                        // prevent button displaying before navigation completed
                        delay(100)
                        _loadingIsVisible.emit(false)
                    }
                }
            } catch (e: Exception) {
                Log.e(MainViewModel::class.java.simpleName, "getDogImageUsecase failed", e)
            }
        }
    }
}