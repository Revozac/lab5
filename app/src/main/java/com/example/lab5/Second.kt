package com.example.lab5

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Second : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Устанавливаем макет для второй активности
        setContentView(R.layout.activity_second)

        // Получаем итоговую сумму, переданную из MainActivity
        val finalAmount = intent.getDoubleExtra("finalAmount", 0.0)

        // Находим TextView и отображаем результат
        val resultTextView = findViewById<TextView>(R.id.resultText)
        resultTextView.text = "По истечению срока Вашего вклада можно получить $finalAmount рублей. Нажмите, если Вы согласны."
    }
}