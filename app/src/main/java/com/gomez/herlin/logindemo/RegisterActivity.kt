package com.gomez.herlin.logindemo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gomez.herlin.logindemo.service.LoginService

class RegisterActivity : AppCompatActivity() {
    var loginService: LoginService? = null

    var textViewUsername: EditText? = null
    var textViewPassword: EditText? = null
    var textViewConfirmPassword: EditText? = null
    var btnConfirm: Button? = null

    var tv_login: TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginService = LoginService(this)
        textViewUsername = findViewById<View>(R.id.username) as EditText
        textViewPassword = findViewById<View>(R.id.password) as EditText
        textViewConfirmPassword = findViewById<View>(R.id.confirmPassword) as EditText
        btnConfirm = findViewById<View>(R.id.btnConfirm) as Button
        tv_login = findViewById(R.id.tv_login)

        tv_login?.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        btnConfirm!!.setOnClickListener {
            val username = textViewUsername!!.text.toString()
            val password = textViewPassword!!.text.toString()
            val confirm_password = textViewConfirmPassword!!.text.toString()
            if (username == "") {
                Toast.makeText(applicationContext, R.string.errorRequiredUser, Toast.LENGTH_LONG)
                    .show()
            } else if (password == "") {
                Toast.makeText(
                    applicationContext,
                    R.string.errorRequiredPassword,
                    Toast.LENGTH_LONG
                ).show()
            } else if (confirm_password == "") {
                Toast.makeText(
                    applicationContext,
                    R.string.errorRequiredConfirmPassword,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (username.length < 8) {
                    Toast.makeText(
                        applicationContext,
                        R.string.errorMin8Characters,
                        Toast.LENGTH_LONG
                    ).show()
                } else if (password.length < 6) {
                    Toast.makeText(
                        applicationContext,
                        R.string.errorMin6Characters,
                        Toast.LENGTH_LONG
                    ).show()
                } else if (password == confirm_password) {
                    val verifyuser = loginService!!.verifyUser(username)
                    if (verifyuser == true) {
                        val insert = loginService!!.Insert(username, password)
                        if (insert == true) {
                            Toast.makeText(
                                applicationContext,
                                R.string.txtRegisterSuccess,
                                Toast.LENGTH_LONG
                            ).show()
                            textViewUsername!!.setText("")
                            textViewPassword!!.setText("")
                            textViewConfirmPassword!!.setText("")
                            val intent = Intent(
                                this@RegisterActivity,
                                MainActivity::class.java
                            )
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            R.string.txtUsernameInUse,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        R.string.txtPasswordMismatch,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}