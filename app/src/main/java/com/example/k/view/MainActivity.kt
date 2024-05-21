package com.example.k.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.k.Login.CadastroActivity
import com.example.k.Login.LoginActivity
import com.example.k.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.containerLogin)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtendo referências aos botões
        val buttonCadastrar = findViewById<Button>(R.id.buttonCadastrar)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        // Configurando os ouvintes de clique para os botões
        buttonCadastrar.setOnClickListener {
            // Criando um intent para iniciar a Activity de cadastro
            val intent = Intent(this, CadastroActivity::class.java)
            // Iniciando a Activity de cadastro
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            // Criando um intent para iniciar a Activity de login
            val intent = Intent(this, LoginActivity::class.java)
            // Iniciando a Activity de login
            startActivity(intent)
        }
    }
}
