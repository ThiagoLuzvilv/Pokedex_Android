    package com.example.k.Login

    import android.content.Intent
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.example.k.R
    import com.example.k.view.SecondActivity
    import kotlinx.coroutines.Dispatchers
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.launch

    class CadastroActivity : AppCompatActivity() {

        private lateinit var editTextUsername: EditText
        private lateinit var editTextPassword: EditText
        private lateinit var buttonLogin: Button
        private lateinit var userRepository: UserRepository

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_cadastro)

            userRepository = UserRepository(this)

            editTextUsername = findViewById(R.id.editUsername)
            editTextPassword = findViewById(R.id.editPassword)
            buttonLogin = findViewById(R.id.button)

            buttonLogin.setOnClickListener {
                val username = editTextUsername.text.toString().trim()
                val password = editTextPassword.text.toString().trim()

                if (username.isNotEmpty() && password.isNotEmpty()) {
                    val user = User(username = username, password = password)
                    GlobalScope.launch(Dispatchers.IO) {
                        userRepository.insertUser(user)
                    }

                    val intent = Intent(this@CadastroActivity, SecondActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@CadastroActivity,
                        "Por favor, preencha todos os campos.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
