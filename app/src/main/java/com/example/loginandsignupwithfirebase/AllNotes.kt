package com.example.loginandsignupwithfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandsignupwithfirebase.databinding.ActivityAllNotesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNotes : AppCompatActivity() {
    private val binding : ActivityAllNotesBinding by lazy {
        ActivityAllNotesBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.noteRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
       // Initialize Firebase database reference

        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()


        val currentUser = auth.currentUser
        currentUser?.let { user->
            val noteReference = databaseReference.child("users").child(user.uid).child("notes")
            noteReference.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                     val noteList = mutableListOf<Noteitem>()
                    for (noteSnapshot in snapshot.children){
                        val note = noteSnapshot.getValue(Noteitem::class.java)
                        note?.let {
                            noteList.add(it)
                        }
                    }
                    val adapter = NoteAdapter(noteList)
                    recyclerView.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }
}