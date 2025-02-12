package com.example.diseoaplicacionbienestar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class MeditacionGuiadaActivity : AppCompatActivity() {
    private lateinit var tipoMeditacion: String
    private var duracion: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meditation)

        val btnRelajacion = findViewById<Button>(R.id.btnRelajacion)
        val btnEnfoque = findViewById<Button>(R.id.btnEnfoque)
        val radioGroupDuracion = findViewById<RadioGroup>(R.id.radioGroupDuracion)
        val btnIniciarMeditacion = findViewById<Button>(R.id.btnIniciarMeditacion)

        btnRelajacion.setOnClickListener { tipoMeditacion = "Relajación" }
        btnEnfoque.setOnClickListener { tipoMeditacion = "Enfoque" }

        radioGroupDuracion.setOnCheckedChangeListener { _, checkedId ->
            duracion = when (checkedId) {
                R.id.rb5Min -> 5
                R.id.rb10Min -> 10
                R.id.rb15Min -> 15
                else -> 5
            }
        }

        btnIniciarMeditacion.setOnClickListener {
            val intent = Intent(this, TemporizadorActivity::class.java)
            intent.putExtra("TIPO_MEDITACION", tipoMeditacion)
            intent.putExtra("DURACION", duracion)
            startActivity(intent)
        }

        val btnVolverAlMenu = findViewById<Button>(R.id.btnVolver)
        btnVolverAlMenu.setOnClickListener {
            finish()
        }
    }
}
