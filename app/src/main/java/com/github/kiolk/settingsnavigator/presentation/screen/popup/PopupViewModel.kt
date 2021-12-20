package com.github.kiolk.settingsnavigator.presentation.screen.popup

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.kiolk.settingsnavigator.presentation.base.viewModel.BaseViewModel

class PopupViewModel(context: Context) : BaseViewModel(context) {

    private lateinit var handler: Handler
    private lateinit var thread: Thread

    private val _isAllow: MutableLiveData<Boolean> = MutableLiveData()
    val isAllow: LiveData<Boolean> = _isAllow

    fun startHandleAllow() {
        handler = Handler(Looper.getMainLooper(), null)
        thread = Thread {
            try {
                while (checkPermission().not()) {
                    Thread.sleep(CHECK_PERMISSION_RETRY_DELAY)
                }

                handler.post {
                    _isAllow.value = true
                }
            } catch (exception: Exception) {

            }
        }

        thread.start()
    }

    fun stopHandleAllow() {
        thread.interrupt()
    }

    companion object {
        private const val CHECK_PERMISSION_RETRY_DELAY = 1000L
    }
}