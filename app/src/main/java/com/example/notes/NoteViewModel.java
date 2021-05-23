package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public LiveData<List<Note>> allNote;
    public NoteRepository mRepository;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        this.allNote=mRepository.getAllWords();

    }
    public void delete(Note note){
        mRepository.delete(note);
    }

    public void insert(Note note){
        mRepository.insert(note);
    }

    public void update(Note note,int id){
        mRepository.update(note,id);
    }
}
