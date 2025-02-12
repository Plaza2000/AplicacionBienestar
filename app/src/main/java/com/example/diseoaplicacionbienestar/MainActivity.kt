package com.example.diseoaplicacionbienestar




import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verificar si el usuario ya ha iniciado sesión antes
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val loggedInUser = sharedPreferences.getString("username", null)

        if (loggedInUser != null) {
            startActivity(Intent(this, MenuActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        val dbHelper = DatabaseHelper(this)
        val etUsername = findViewById<EditText>(R.id.etLoginUsername)
        val etPassword = findViewById<EditText>(R.id.etLoginPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnGoToRegister = findViewById<Button>(R.id.btnGoToRegister)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (dbHelper.checkUser(username, password)) {
                // Guardar sesión con SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("username", username)
                editor.apply()

                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MenuActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        btnGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
