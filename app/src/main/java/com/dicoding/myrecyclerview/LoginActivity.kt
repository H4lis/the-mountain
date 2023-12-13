package com.dicoding.myrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.widget.addTextChangedListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsrname: EditText
    private lateinit var etPasswordd: EditText
    private lateinit var btnLogin: Button
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        etUsrname = findViewById(R.id.edt_nameLogin)
        etPasswordd = findViewById(R.id.edt_passLogin)
        btnLogin = findViewById(R.id.btn_Login)




        btnLogin.setOnClickListener(View.OnClickListener {

            val username = etUsrname.text.toString()
            val password = etPasswordd.text.toString()

            database = FirebaseDatabase.getInstance().getReference("users")

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong!!", Toast.LENGTH_SHORT).show()
            }else{
                database.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.child(username).exists()) {
                            val userPassword = snapshot.child(username).child("password").getValue(String::class.java)
                            if (userPassword.equals(password, ignoreCase = true)) {
                                Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(applicationContext, "userPassword: $userPassword, password: $password", Toast.LENGTH_LONG).show()
                                Toast.makeText(applicationContext, "Password salah", Toast.LENGTH_SHORT).show()
                            }
                        } else {

                            Toast.makeText(applicationContext, "Data anda belum terdaftar", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        // Handle errors here
                    }
                })
            }
        })









        val tvRegister = findViewById<TextView>(R.id.tv_daftar)
        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
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


            val newIcon = if (etPassword.transformationMethod != null) {
                R.drawable.ic_eye_closed
            } else {
                R.drawable.ic_eye
            }
            etPassword.setSelection(etPassword.text.length)
            btnTogglePassword.setImageResource(newIcon)

        }


    }
}