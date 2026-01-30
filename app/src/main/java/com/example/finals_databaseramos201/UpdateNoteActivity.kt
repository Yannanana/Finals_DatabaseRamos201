package com.example.finals_databaseramos201

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.finals_databaseramos201.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateNoteBinding
    private lateinit var db : NotesDatabaseHelper
    private var noteId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        db = NotesDatabaseHelper(this)
        noteId = intent.getIntExtra("notes_id", -1)
        if(noteId==-1) {
            finish()
            return
        }
        val note = db.getNoteByID(noteId)
        binding.ETUpdateTitle.setText(note.title)
        binding.ETUpdateContent.setText(note.content)
        binding.IVUpdateButton.setOnClickListener {
            val newTitle = binding.ETUpdateTitle.text.toString()
            val newContent = binding.ETUpdateContent.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            db.updatenote(updateNote)
            finish()
            Toast.makeText(this, "Change Saved", Toast.LENGTH_SHORT).show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}