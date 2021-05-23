package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onClicked{
    private NoteViewModel noteViewModel;
    private NotesAdapter notesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycle);
        notesAdapter=new NotesAdapter(new NotesAdapter.WordDiff(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);
        noteViewModel= new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NoteViewModel.class);
        noteViewModel.allNote.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {

                notesAdapter.update(notes);

            }
        });


    }

    @Override
    public void onClicking(Note note) {
              noteViewModel.delete(note);
        Toast.makeText(this,note.getNote()+" deleted",Toast.LENGTH_SHORT).show();
    }
    public void submitup(View view){
        EditText editText=(EditText)findViewById(R.id.edittext);
        String text=editText.getText().toString();
        if(!TextUtils.isEmpty(text)){
            noteViewModel.insert(new Note(text));
            editText.getText().clear();
            Toast.makeText(this,text+" inserted",Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public void onClickingtick(Note note,String text) {
        EditText editText=findViewById(R.id.editnote);
        ImageView b=findViewById(R.id.buttonedit);
        if(!TextUtils.isEmpty(text)) {
            noteViewModel.update(new Note(text), note.getId());
            Toast.makeText(this,note.getNote()+" updated",Toast.LENGTH_SHORT).show();
            editText.getText().clear();
            editText.setVisibility(View.GONE);
            b.setVisibility(View.GONE);
        }
    }
}