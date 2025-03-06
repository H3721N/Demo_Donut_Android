package com.gomez.herlin.logindemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gomez.herlin.logindemo.R
import com.gomez.herlin.logindemo.service.LoginService

class MainActivity : AppCompatActivity() {
    private lateinit var tvRegister: TextView
    private lateinit var btnLogin: Button
    private lateinit var textViewUsername: EditText
    private lateinit var textViewPassword: EditText

    private lateinit var loginService: LoginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginService = LoginService(this)
        textViewUsername = findViewById(R.id.username)
        textViewPassword = findViewById(R.id.password)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tv_register)


        tvRegister.setOnClickListener {
            val intentReg = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intentReg)
        }

        btnLogin.setOnClickListener {
            val username = textViewUsername.text.toString()
            val password = textViewPassword.text.toString()

            when {
                username.isEmpty() -> Toast.makeText(applicationContext, R.string.errorRequiredUser, Toast.LENGTH_LONG).show()
                password.isEmpty() -> Toast.makeText(applicationContext, R.string.errorRequiredPassword, Toast.LENGTH_LONG).show()
                username.length < 8 -> Toast.makeText(applicationContext, R.string.errorMin8Characters, Toast.LENGTH_LONG).show()
                password.length < 6 -> Toast.makeText(applicationContext, R.string.errorMin6Characters, Toast.LENGTH_LONG).show()
                else -> {
                    val checkLogin = loginService.verifyLogin(username, password)
                    if (checkLogin) {
                        Toast.makeText(applicationContext, R.string.txtViewWelcome, Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, HomeActivity::class.java)
                        intent.putExtra("username", username)
                        startActivity(intent)
                    } else {
                        Toast.makeText(applicationContext, R.string.errorInvalidUser, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    //class ListElement
}


