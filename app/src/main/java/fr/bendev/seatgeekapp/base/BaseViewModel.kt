package fr.bendev.seatgeekapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.bendev.seatgeekapp.domain.common.error.ErrorType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel() {

    protected val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    protected val _modelError = MutableStateFlow<ErrorType?>(null)
    val modelError: StateFlow<ErrorType?> = _modelError

    open fun dismissError() {
        _modelError.value = null
    }

}

class BaseViewModelFactory<T : ViewModel>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return creator() as T
    }
}