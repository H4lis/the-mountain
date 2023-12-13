package com.dicoding.myrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvRegister = findViewById<TextView>(R.id.tv_daftar)
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        val btn_mainActivity = findViewById<Button>(R.id.btn_Login)
        btn_mainActivity.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }












        val etPassword = findViewById<EditText>(R.id.edt_pass)
        val btnTogglePassword = findViewById<ImageButton>(R.id.btnTogglePassword)

        // Set a listener to toggle password visibility
        btnTogglePassword.setOnClickListener {
            // Toggle password visibility
            etPassword.transformationMethod =
                if (etPassword.transformationMethod == null)
                    PasswordTransformationMethod.getInstance()
                else null

            // Move the cursor to the end of the text to keep the visual cursor position


            val newIcon = if (etPassword.transformationMethod == null) {
                R.drawable.ic_eye_closed
            } else {
                R.drawable.ic_eye
            }
            etPassword.setSelection(etPassword.text.length)
            btnTogglePassword.setImageResource(newIcon)

        }


    }
}