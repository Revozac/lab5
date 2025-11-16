package com.example.lab5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
             ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализация элементов интерфейса
        val depositAmount = findViewById<EditText>(R.id.depositAmount)
        val termGroup = findViewById<RadioGroup>(R.id.termGroup)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        // Устанавливаем слушатель на кнопку OK
        calculateButton.setOnClickListener {
            // Получаем сумму вклада из поля ввода
            val amountText = depositAmount.text.toString()

            // Проверяем, что пользователь ввел сумму
            if (amountText.isEmpty()) {
                depositAmount.error = "Введите сумму"
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()

            // Проверка, что введено корректное число
            if (amount == null) {
                depositAmount.error = "Некорректное значение"
                return@setOnClickListener
            }

            var interestRate = 0.0

            // Определяем процентную ставку в зависимости от выбранного срока
            when (termGroup.checkedRadioButtonId) {
                R.id.radio_3months -> interestRate = 0.03 // 3 месяца = 3%
                R.id.radio_6months -> interestRate = 0.05 // 6 месяцев = 5%
                R.id.radio_12months -> interestRate = 0.09 // год = 9%
                else -> {
                    // Если срок не выбран, выводим ошибку
                    calculateButton.error = "Выберите срок"
                    return@setOnClickListener
                }
            }

            // Рассчитываем итоговую сумму вклада с процентами
            val finalAmount = amount + (amount * interestRate)

            // Переходим на вторую активность, передавая рассчитанную сумму
            val intent = Intent(this, Second::class.java).apply {
                putExtra("finalAmount", finalAmount)
            }
            startActivity(intent)
        }
    }
}
