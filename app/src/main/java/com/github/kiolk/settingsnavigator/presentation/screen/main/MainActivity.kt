package com.github.kiolk.settingsnavigator.presentation.screen.main

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.kiolk.settingsnavigator.R
import com.github.kiolk.settingsnavigator.presentation.screen.popup.PopupActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        subscribe()
    }

    private fun setupUi() {
        findViewById<Button>(R.id.btn_open_settings).setOnClickListener {
            viewModel.openSettingsClick()
        }
    }

    private fun subscribe() {
        viewModel.openSettingsAction.observe(this) {
            openAppSettings()
        }
    }

    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        val uri: Uri = Uri.fromParts(PACKAGE_KEY, packageName, null)
        intent.data = uri

        try {
            startActivities(arrayOf(intent, Intent(this, PopupActivity::class.java)))
        } catch (exception: ActivityNotFoundException) {
            openAppListSettings()
        }
    }

    private fun openAppListSettings() {
        val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
        startActivities(arrayOf(intent, Intent(this, PopupActivity::class.java)))
    }

    companion object {
        private const val PACKAGE_KEY = "package"
    }
}