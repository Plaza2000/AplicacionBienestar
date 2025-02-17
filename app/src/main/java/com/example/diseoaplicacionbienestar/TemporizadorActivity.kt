package com.example.diseoaplicacionbienestar

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class TemporizadorActivity : AppCompatActivity() {
    private lateinit var countdownTimer: CountDownTimer
    private var tiempoRestante: Long = 0
    private var duracion: Long = 0
    private var isPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temporizador)

        val tipoMeditacion = intent.getStringExtra("TIPO_MEDITACION") ?: ""
        duracion = intent.getIntExtra("DURACION", 5) * 60 * 1000L
        tiempoRestante = duracion

        val textTipo = findViewById<TextView>(R.id.textTipoMeditacion)
        val textTiempo = findViewById<TextView>(R.id.textTemporizador)
        val textDuracion = findViewById<TextView>(R.id.textDuracionMeditacion)
        val btnPausar = findViewById<Button>(R.id.btnPausar)
        val btnReanudar = findViewById<Button>(R.id.btnReanudar)
        val btnTerminar = findViewById<Button>(R.id.btnTerminar)

        textTipo.text = "Meditación: $tipoMeditacion"
        textDuracion.text = "Duración: ${TimeUnit.MILLISECONDS.toMinutes(duracion)} min"

        iniciarTemporizador(textTiempo)

        // Pausar el temporizador
        btnPausar.setOnClickListener {
            pausarTemporizador()
            btnPausar.visibility = View.GONE
            btnReanudar.visibility = View.VISIBLE
            btnTerminar.visibility = View.VISIBLE
        }

        // Reanudar el temporizador
        btnReanudar.setOnClickListener {
            continuarTemporizador()
            btnReanudar.visibility = View.GONE
            btnTerminar.visibility = View.GONE
            btnPausar.visibility = View.VISIBLE
        }

        // Terminar la meditación
        btnTerminar.setOnClickListener {
            countdownTimer.cancel()
            finish()  // Regresa a la actividad anterior
        }
    }

    private fun iniciarTemporizador(textView: TextView) {
        countdownTimer = object : CountDownTimer(tiempoRestante, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tiempoRestante = millisUntilFinished
                val minutos = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val segundos = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                textView.text = "%02d:%02d".format(minutos, segundos)
            }

            override fun onFinish() {

                textView.text = "Meditación finalizada"
                finish()
            }
        }
        countdownTimer.start()
        isPaused = false
    }

    private fun pausarTemporizador() {
        countdownTimer.cancel()
        isPaused = true
    }

    private fun continuarTemporizador() {
        iniciarTemporizador(findViewById(R.id.textTemporizador))
    }
}
