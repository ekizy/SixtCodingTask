package com.ekiz.sixtcodingtask.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel(private val coroutineScopeProvider: CoroutineScope? = null) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    var bgScope = CoroutineScope(Dispatchers.Default + viewModelJob)


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}