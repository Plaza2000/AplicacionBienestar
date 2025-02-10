package com.example.diseoaplicacionbienestar


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        val btnMeditacion = findViewById<Button>(R.id.btnMeditacion)
        val btnEstadoAnimo = findViewById<Button>(R.id.btnEstadoAnimo)
        val btnCalculadoraSueno = findViewById<Button>(R.id.btnCalculadoraSueno)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Ir a Meditación
        btnMeditacion.setOnClickListener {
            startActivity(Intent(this, MeditacionGuiadaActivity::class.java))
        }

        // Ir a Estado de Ánimo
       btnEstadoAnimo.setOnClickListener {
           startActivity(Intent(this, MoodTracker::class.java))
        }

        // Ir a Calculadora del Sueño
        btnCalculadoraSueno.setOnClickListener {
            startActivity(Intent(this, CalculadoraSueno::class.java))
        }

        // Logout (Cerrar sesión)
        btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.remove("username")
            editor.apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
