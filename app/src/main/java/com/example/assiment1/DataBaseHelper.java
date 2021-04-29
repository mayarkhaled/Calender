package com.example.assiment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String NOTEBOOK_TABLE = "NOTEBOOK_TABLE";
    public static final String COLUMN_NOTEBOOK_NAME = "NOTEBOOK_NAME";
    public static final String COLUMN_IS_LIST = "IS_LIST";
    public static final String COLUMN_NOTEBOOK_ID = "NOTEBOOK_ID";
    public static final String COLUMN_NOTEBOOK_COLOR = "NOTEBOOK_COLOR";
    public static final String COLUMN_PICTURE_LOCATION = "PICTURE_LOCATION";

    public static final String NOTE_TABLE = "NOTE_TABLE";
    public static final String COLUMN_NOTE_ID = "NOTE_ID";
    public static final String COLUMN_BOOK_ID = "BOOK_ID";
    public static final String COLUMN_NOTE_TEXT = "NOTE_TEXT";
    public static final String COLUMN_DUE_DATE = "DUE_DATE";
    public static final String COLUMN_IS_COMPLETED = "IS_COMPLETED";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "notebook.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createNotebookTableStatement = "CREATE TABLE "
                + NOTEBOOK_TABLE
                + " (" + COLUMN_NOTEBOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOTEBOOK_NAME + " TEXT, "
                + COLUMN_NOTEBOOK_COLOR + " TEXT, "
                + COLUMN_PICTURE_LOCATION + " TEXT, "
                + COLUMN_IS_LIST + " BOOLEAN)";

        String createNoteTableStatement = "CREATE TABLE "
                + NOTE_TABLE
                + "(" + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_BOOK_ID + " INTEGER, "
                + COLUMN_NOTE_TEXT + " TEXT, "
                + COLUMN_DUE_DATE + " TEXT, "
                + COLUMN_IS_COMPLETED + " BOOLEAN, "
                + " FOREIGN KEY (" + COLUMN_BOOK_ID + ") REFERENCES " + NOTEBOOK_TABLE + "(" + COLUMN_NOTEBOOK_ID + "));";

        db.execSQL(createNotebookTableStatement);
        db.execSQL(createNoteTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //NOTES***************************************************************

    public boolean addNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BOOK_ID, note.getBookId());
        cv.put(COLUMN_NOTE_TEXT, note.getNoteText());
        cv.put(COLUMN_DUE_DATE, note.getDueDate());
        cv.put(COLUMN_IS_COMPLETED, note.isCompleted());
        long insert = db.insert(NOTE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Note> getNotes() {
        List<Note> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + NOTE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int noteId = cursor.getInt(0);
                int bookId = cursor.getInt(1);
                String noteText = cursor.getString(2);
                String dueDate = cursor.getString(3);
                boolean isCompleted = cursor.getInt(4) == 1 ? true : false;

                Note note = new Note(noteId, bookId, noteText, dueDate, isCompleted);
                returnList.add(note);

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public  List<Note> getNotes(String dueDate) {
        List<Note> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + NOTE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int noteId = cursor.getInt(0);
                int bookId = cursor.getInt(1);
                String noteText = cursor.getString(2);
                String date = cursor.getString(3);
                boolean isCompleted = cursor.getInt(4) == 1 ? true : false;

                Note note = new Note(noteId, bookId, noteText, dueDate, isCompleted);
                if (date.equals(dueDate)){
                    returnList.add(note);}

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<Note> getNotes(int notebookId) {
        List<Note> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + NOTE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int noteId = cursor.getInt(0);
                int bookId = cursor.getInt(1);
                String noteText = cursor.getString(2);
                String dueDate = cursor.getString(3);
                boolean isCompleted = cursor.getInt(4) == 1 ? true : false;

                Note note = new Note(noteId, bookId, noteText, dueDate, isCompleted);
                if (note.getBookId() == notebookId){
                    returnList.add(note);}

            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean deleteNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NOTE_TABLE + " WHERE NOTE_ID  = " + note.getNoteId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }

    public boolean updateNoteIsCompleted(Note note, boolean isCompleted){
        int noteId = note.getNoteId();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_IS_COMPLETED, isCompleted);
        db.update(NOTE_TABLE, cv, "NOTE_ID = ?", new String[]{String.valueOf(noteId)});
        return true;
    }

    private boolean deleteNotesByNotebook(int bookId){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + NOTE_TABLE + " WHERE " + COLUMN_BOOK_ID + " = " + bookId;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }

    }


}