package com.caren.simpletodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 3000
    private var mDelayHandler: Handler? = null
    private var progressBarStatus = 0
    var dummy:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
    }



    private fun launchMainActivity() {
        var intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        this.finish()
        mDelayHandler!!.removeCallbacks(mRunnable)

    }

    private val mRunnable: Runnable = Runnable {

        Thread(Runnable {
            while (progressBarStatus < 100) {

                try {
                    dummy += 25
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                progressBarStatus = dummy

                splash_screen_progress_bar.progress = progressBarStatus
            }

            launchMainActivity()

        }).start()
    }

    override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}


