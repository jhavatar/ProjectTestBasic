package io.chthonic.projecttestbasic.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.chthonic.projecttestbasic.domain.GetDogImageUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDogImageUsecase: GetDogImageUsecase
) : ViewModel() {

    sealed class NavigationTarget() {
        data class ImageScreen(val url: String) : NavigationTarget()
    }

    private val _loadingIsVisible = MutableStateFlow<Boolean>(false)
    val loadingIsVisible: StateFlow<Boolean>
        get() = _loadingIsVisible

    private val _navigate = MutableStateFlow<NavigationTarget?>(null)
    val navigate: StateFlow<NavigationTarget?>
        get() = _navigate

    fun onNavigationObserved() {
        _navigate.value = null
    }

    suspend fun onImageButtonClicked() {
        try {
            _loadingIsVisible.value = true
            getDogImageUsecase.execute().let { dogImage ->
                _navigate.value = NavigationTarget.ImageScreen(dogImage.url)
                _loadingIsVisible.value = false
            }
        } catch (e: Exception) {
            Log.e(MainViewModel::class.java.simpleName, "getDogImageUsecase failed", e)
        }
    }
}