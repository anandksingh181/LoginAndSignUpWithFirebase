package com.example.loginandsignupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginandsignupwithfirebase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.createNoteButton.setOnClickListener {
            startActivity(Intent(this , AddNote::class.java))
        }
        binding.openNoteButton.setOnClickListener {
            startActivity(Intent(this , AllNotes::class.java))
        }
    }
}