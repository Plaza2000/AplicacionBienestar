package com.example.diseoaplicacionbienestar

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class CalculadoraSueno : AppCompatActivity() {
    private lateinit var timePicker: TimePicker
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_calculator)

        timePicker = findViewById(R.id.TimePicker)
        textResultado = findViewById(R.id.textResultado)
        val btnCalcular = findViewById<Button>(R.id.btnCalcular)

        btnCalcular.setOnClickListener {
            calcularHoraDormir()
        }

        val btnVolverAlMenu = findViewById<Button>(R.id.btnVolver)
        btnVolverAlMenu.setOnClickListener {
            finish()  // Esto cerrará la actividad de Meditación y regresará al menú
        }
    }

    private fun calcularHoraDormir() {
        val hora = timePicker.hour
        val minuto = timePicker.minute
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hora)
        calendar.set(Calendar.MINUTE, minuto)

        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())

        val hora8 = formatter.format(calendar.timeInMillis - 8 * 3600000)

        textResultado.text = "$hora8"
    }
}
