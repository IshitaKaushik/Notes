package com.example.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allWords;
    NoteRepository(Application application){
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        noteDao=noteDatabase.noteDao();
        allWords=noteDao.getAllNotes();
    }

        void insert(Note note){
        NoteDatabase.databaseWriter.execute(() -> noteDao.insert(note));

        }
        void delete(Note note){
            NoteDatabase.databaseWriter.execute(() -> noteDao.delete(note));
        }

        void update(Note note,int id){
            NoteDatabase.databaseWriter.execute(() -> noteDao.update(note.getNote(),id));
        }
    LiveData<List<Note>> getAllWords(){
        return allWords;
    }
}
