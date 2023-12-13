    package com.dicoding.myrecyclerview
    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.text.method.PasswordTransformationMethod
    import android.widget.Button
    import android.widget.EditText
    import android.widget.ImageButton
    import android.widget.TextView
    import android.widget.Toast
    import android.view.View
    import com.google.firebase.database.DatabaseReference
    import com.google.firebase.database.FirebaseDatabase

    class RegisterActivity : AppCompatActivity() {

        private lateinit var etUsername: EditText
        private lateinit var etEmail: EditText
        private lateinit var etPasswordd: EditText
        private lateinit var btnRegister: Button

        private lateinit var database: DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_register)

            etUsername = findViewById(R.id.edt_nama)
            etEmail = findViewById(R.id.edt_email)
            etPasswordd = findViewById(R.id.edt_pass)
            btnRegister = findViewById(R.id.btn_daftar)

            database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://themountain-817b6-default-rtdb.firebaseio.com/")

            btnRegister.setOnClickListener(View.OnClickListener {
                val username = etUsername.text.toString()
                val email = etEmail.text.toString()
                val password = etPasswordd.text.toString()

                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong!!", Toast.LENGTH_SHORT).show()
                } else {
                    database = FirebaseDatabase.getInstance().getReference("users")
                    database.child(username).child("username").setValue(username)
                    database.child(username).child("email").setValue(email)
                    database.child(username).child("password").setValue(password)

                    Toast.makeText(applicationContext, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val register = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(register)
                }
            })



            val tvRegister = findViewById<TextView>(R.id.tv_login)
            tvRegister.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }



            val etPassword = findViewById<EditText>(R.id.edt_pass)
            val etPassword2 = findViewById<EditText>(R.id.edt_confirmPass)
            val btnTogglePassword = findViewById<ImageButton>(R.id.btnTogglePassword)
            val btnTogglePassword2 = findViewById<ImageButton>(R.id.btnTogglePassword2)

            // Set a listener to toggle password visibility
            btnTogglePassword.setOnClickListener {
                // Toggle password visibility
                etPassword.transformationMethod =
                    if (etPassword.transformationMethod == null)
                        PasswordTransformationMethod.getInstance()
                    else null

                // Move the cursor to the end of the text to keep the visual cursor position


                val newIcon = if (etPassword.transformationMethod == null) {
                    R.drawable.ic_eye
                } else {
                    R.drawable.ic_eye_closed
                }
                etPassword.setSelection(etPassword.text.length)
                btnTogglePassword.setImageResource(newIcon)

            }
            btnTogglePassword2.setOnClickListener {

                // Toggle password visibility
                etPassword2.transformationMethod =
                    if (etPassword2.transformationMethod == null)
                        PasswordTransformationMethod.getInstance()
                    else null

                // Move the cursor to the end of the text to keep the visual cursor position


                val newIcon = if (etPassword2.transformationMethod == null) {
                    R.drawable.ic_eye
                } else {
                    R.drawable.ic_eye_closed
                }
                etPassword2.setSelection(etPassword.text.length)
                btnTogglePassword2.setImageResource(newIcon)

            }


        }
    }