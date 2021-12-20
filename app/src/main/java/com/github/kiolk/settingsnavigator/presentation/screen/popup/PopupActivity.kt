package com.github.kiolk.settingsnavigator.presentation.screen.popup

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.kiolk.settingsnavigator.R
import com.github.kiolk.settingsnavigator.presentation.screen.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class PopupActivity : AppCompatActivity() {

    private val viewModel: PopupViewModel by viewModel()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.view_popup)
        configureScreen()
        setupUi()
        subscribe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.startHandleAllow()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopHandleAllow()
        openIfNavigateDeep()
    }

    private fun configureScreen() {
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.BOTTOM
        this.window.attributes = params
    }

    private fun setupUi() {
        findViewById<TextView>(R.id.tv_close_popup).setOnClickListener {
            hideScreen()
            checkOpenOnTop()
        }
    }

    private fun hideScreen() {
        findViewById<ConstraintLayout>(R.id.cl_popup_root_view).visibility = View.INVISIBLE
    }

    private fun subscribe() {
        viewModel.isAllow.observe(this) {
            findViewById<TextView>(R.id.tv_application_status_popup).text =
                getString(R.string.allowed_popup)
            backToStartScreen()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    private fun backToStartScreen() {
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        })
    }

    private fun openIfNavigateDeep() {
        windowManager
        //TODO logic for understand navigation deep
        if (checkOpenOnTop()) {
//            startActivity(Intent(this, PopupActivity::class.java))
        }
    }

    private fun checkOpenOnTop(): Boolean {
        val am = baseContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val allTasks = am.getRunningTasks(100)
        val last = allTasks.last()
        return !last.topActivity?.className.equals(MainActivity::class.java.name)
    }
}