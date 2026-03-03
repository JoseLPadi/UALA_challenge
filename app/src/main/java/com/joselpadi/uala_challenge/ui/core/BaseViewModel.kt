package com.joselpadi.uala_challenge.ui.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel : ViewModel() {
    internal val _loadingDialog = MutableStateFlow<Boolean>(false)
    val loadingDialog: StateFlow<Boolean> = _loadingDialog.asStateFlow()


}