package com.github.kiolk.settingsnavigator.presentation.screen.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.kiolk.settingsnavigator.presentation.base.viewModel.BaseViewModel

class MainViewModel(context: Context) : BaseViewModel(context) {

    private val _openSettingsAction: MutableLiveData<Any> = MutableLiveData()
    val openSettingsAction: LiveData<Any> = _openSettingsAction

    fun openSettingsClick() {
        if (checkPermission().not()) {
            _openSettingsAction.value = Any()
        }
    }
}