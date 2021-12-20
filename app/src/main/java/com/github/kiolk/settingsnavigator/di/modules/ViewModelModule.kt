package com.github.kiolk.settingsnavigator.di.modules

import com.github.kiolk.settingsnavigator.presentation.screen.main.MainViewModel
import com.github.kiolk.settingsnavigator.presentation.screen.popup.PopupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModuleModel = module {
    viewModel { MainViewModel(get()) }
    viewModel { PopupViewModel(get()) }
}