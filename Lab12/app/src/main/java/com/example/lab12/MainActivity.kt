package com.example.lab12

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // 啟用沉浸式體驗
        setContentView(R.layout.activity_main)

        // 設定系統邊框處理
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 設定按鈕監聽器
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            startMyService() // 啟動 Service 的抽取函式
        }
    }

    // 啟動 Service 的方法
    private fun startMyService() {
        try {
            // 初始化 Intent 並啟動 Service
            val serviceIntent = Intent(this, MyService::class.java).apply {
                // 可傳遞資料至 Service
                putExtra("EXTRA_DATA", "Some data")
            }
            startService(serviceIntent)

            // 顯示啟動成功訊息
            Toast.makeText(this, "啟動 Service", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            // 捕獲例外並顯示錯誤訊息
            Toast.makeText(this, "啟動 Service 時發生錯誤: ${e.message}", Toast.LENGTH_LONG).show()
        } finally {
            // 關閉當前 Activity
            finish()
        }
    }
}
