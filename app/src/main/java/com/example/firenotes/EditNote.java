package com.example.firenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;


public class EditNote extends AppCompatActivity {
    Intent data;
    EditText editNoteTitle, editNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.editToolbar);
        setSupportActionBar(toolbar);

        data = getIntent();
        editNoteTitle =  findViewById(R.id.editNoteTitle);
        editNoteContent = findViewById(R.id.editNoteContent);

        String title = data.getStringExtra("title");
        String content = data.getStringExtra("content");

        editNoteTitle.setText(title);
        editNoteContent.setText(content);

    }
}