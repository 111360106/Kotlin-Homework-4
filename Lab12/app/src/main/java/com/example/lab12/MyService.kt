package com.example.lab12

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Thread {
            try {
                Thread.sleep(3000) // 延遲三秒
                startActivity(Intent(this, SecActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null
}

