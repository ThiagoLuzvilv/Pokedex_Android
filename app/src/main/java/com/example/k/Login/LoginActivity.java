package com.example.k.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.k.R;
import com.example.k.view.SecondActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextUsername = findViewById(R.id.editUsername);
        editTextPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                // Verificando se os campos de usuário e senha estão preenchidos
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    // Se ambos os campos estiverem preenchidos, iniciar a SecondActivity
                    Intent intent = new Intent(LoginActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else {
                    // Se algum dos campos estiver vazio, exibir uma mensagem de erro usando um Toast
                    Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
