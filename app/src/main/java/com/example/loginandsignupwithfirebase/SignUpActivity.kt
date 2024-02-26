package com.example.loginandsignupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.loginandsignupwithfirebase.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding : ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth

        auth = FirebaseAuth.getInstance()


        binding.signInButton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        binding.registerButton.setOnClickListener {
            val email = binding.email.text.toString()
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()
            val repeatPassword = binding.repeatPassword.text.toString()

            if (email.isEmpty() || userName.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()){
                Toast.makeText(this, "Please Fill All The Details", Toast.LENGTH_SHORT).show()
            }else if (password != repeatPassword){
                Toast.makeText(this, "Repeat Password Must be Same", Toast.LENGTH_SHORT).show()
            }else{
                 auth.createUserWithEmailAndPassword(email,password)
                     .addOnCompleteListener(this) { task->
                          if (task.isSuccessful){
                              Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                              startActivity(Intent(this,LoginActivity::class.java))
                              finish()
                          }else{
                              Toast.makeText(this, "Registration Failed : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                          }
                     }
            }
        }
    }
}