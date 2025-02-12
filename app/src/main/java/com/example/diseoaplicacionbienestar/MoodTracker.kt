package com.example.diseoaplicacionbienestar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Toast
import com.example.diseoaplicacionbienestar.MoodAdapter
import com.example.diseoaplicacionbienestar.R

class MoodTracker: AppCompatActivity() {
    private lateinit var adapter: MoodAdapter
    private lateinit var moodList: MutableList<String>

    // Aquí inicializas sharedPreferences correctamente
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener el nombre de usuario desde las preferencias globales
        val globalSharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val username = globalSharedPreferences.getString("username", null)

        // Si no hay usuario logueado, no debe continuar
        if (username == null) {
            Toast.makeText(this, "Debes iniciar sesión", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Inicializas sharedPreferences para cada usuario
        sharedPreferences = getSharedPreferences("${username}_MoodPrefs", Context.MODE_PRIVATE)

        setContentView(R.layout.activity_mood_tracker)

        val btnHappy = findViewById<Button>(R.id.btnHappy)
        val btnNeutral = findViewById<Button>(R.id.btnNeutral)
        val btnSad = findViewById<Button>(R.id.btnSad)
        val btnGuardar = findViewById<Button>(R.id.btnSaveMood)
        val etNota = findViewById<EditText>(R.id.editTextNote)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewHistory)

        moodList = obtenerHistorial()
        adapter = MoodAdapter(moodList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        var moodSeleccionado = ""

        btnHappy.setOnClickListener {
            moodSeleccionado = "😊"
        }

        btnNeutral.setOnClickListener {
            moodSeleccionado = "😐"
        }

        btnSad.setOnClickListener {
            moodSeleccionado = "😢"
        }

        btnGuardar.setOnClickListener {
            if (moodSeleccionado.isNotEmpty()) {
                val nota = etNota.text.toString()
                val currentDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
                val registro = "$moodSeleccionado - ${if (nota.isNotEmpty()) nota else "Sin nota"} - $currentDate"
                guardarRegistro(registro)
                moodList.add(0, registro) // Agregar al inicio
                adapter.notifyItemInserted(0)
                etNota.text.clear()
            }
        }

        val btnVolverAlMenu = findViewById<Button>(R.id.btnVolver)
        btnVolverAlMenu.setOnClickListener {
            finish()  // Esto cerrará la actividad de Meditación y regresará al menú
        }

    }

    private fun obtenerHistorial(): MutableList<String> {
        val historial = sharedPreferences.getStringSet("historial", mutableSetOf()) ?: mutableSetOf()
        return historial.toMutableList()
    }

    private fun guardarRegistro(registro: String) {
        val editor = sharedPreferences.edit()
        val historial = obtenerHistorial()
        historial.add(registro)
        editor.putStringSet("historial", historial.toSet())
        editor.apply()
    }
}
