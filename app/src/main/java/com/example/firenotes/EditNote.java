package com.example.firenotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class EditNote extends AppCompatActivity {
    Intent data;
    EditText editNoteTitle, editNoteContent;
    ProgressBar spinner;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.editToolbar);
        setSupportActionBar(toolbar);

        data = getIntent();
        editNoteTitle =  findViewById(R.id.editNoteTitle);
        editNoteContent = findViewById(R.id.editNoteContent);
        spinner = findViewById(R.id.progressBar2);
        fstore = FirebaseFirestore.getInstance();

        String title = data.getStringExtra("title");
        String content = data.getStringExtra("content");

        editNoteTitle.setText(title);
        editNoteContent.setText(content);

        FloatingActionButton fab = findViewById(R.id.saveNoteBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nTitle = editNoteTitle.getText().toString();
                String nContent = editNoteContent.getText().toString();

                if(nTitle.isEmpty() || nContent.isEmpty()){
                    Toast.makeText(EditNote.this, "EMPTY", Toast.LENGTH_SHORT).show();
                    return;
                }

                spinner.setVisibility(View.VISIBLE);

                DocumentReference docref = fstore.collection("notes").document(data.getStringExtra("noteId"));
                Map<String, Object> note = new HashMap<>();
                note.put("title", nTitle);
                note.put("content", nContent);

                docref.update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditNote.this, "Note updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(view.getContext(), MainActivity.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditNote.this, "Error try again", Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });

    }
}