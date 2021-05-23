package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);
    @Delete
    void delete(Note note);
    @Query("SELECT * FROM notes_table ORDER BY notes_text ASC")
    LiveData<List<Note>> getAllNotes();
    @Query("UPDATE notes_table SET notes_text=:note WHERE id=:id")
    void update(String note,int id);
}
