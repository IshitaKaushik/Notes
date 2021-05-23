package com.example.notes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
     public abstract NoteDao noteDao();
     private static volatile NoteDatabase instance;
     private static final int fixed_threads=4;
     static final ExecutorService databaseWriter = Executors.newFixedThreadPool(fixed_threads);
     static NoteDatabase getInstance(final Context context){
         if(instance == null){
             synchronized (NoteDatabase.class){
                 if(instance == null){
                     instance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database").addCallback(sRoomDatabaseCallback).build();
                 }
             }
         }
         return instance;
     }

     private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
         @Override
         public void onCreate(@NonNull SupportSQLiteDatabase db) {
             super.onCreate(db);
             databaseWriter.execute(() -> {
                 NoteDao dao=instance.noteDao();
                 Note note=new Note("Hello");
                 dao.insert(note);
                 dao.update(note.getNote(),note.getId());
                 dao.delete(note);


             });
         }
     };
}