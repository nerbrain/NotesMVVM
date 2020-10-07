package com.example.architectureexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    private EditText editText_title;
    private EditText editText_description;
    private NumberPicker numberPicker_priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editText_title = findViewById(R.id.title);
        editText_description = findViewById(R.id.description);
        numberPicker_priority = findViewById(R.id.number_picker_priority);

        numberPicker_priority.setMinValue(1);
        numberPicker_priority.setMaxValue(10);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    private void saveNote() {
        String title = editText_title.getText().toString();
        String description = editText_description.getText().toString();
        int priority = numberPicker_priority.getValue();
        Note note = new Note(title,description,priority);

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Insert Title And Description", Toast.LENGTH_SHORT).show();
        } else {
            noteViewModel.insert(note);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}