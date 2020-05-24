package com.example.kjv16;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    Context dbContext;
   String DATABASE_NAME="bible.db";
   String DATABASE_PATH;
    int DB_VERSION=1;
    private static SQLiteDatabase myDatabase;

    public DatabaseHelper(Context context){
        super(context,"bible.db", null, 1);
        this.dbContext=context;
        DATABASE_PATH=dbContext.getDatabasePath("bible.db").getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public List<BookModel> getBooks(){
        List<BookModel>returnList=new ArrayList<>();
        open();
        String query="SELECT * FROM key_english";
        Cursor cursor=myDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                int i = cursor.getInt(0);
                String n = cursor.getString(1);
               // String t=cursor.getString(2);
                //int g=cursor.getInt(3)

                BookModel bookModel=new BookModel(i, n);
                returnList.add(bookModel);

            }while (cursor.moveToNext());
        }else {
        }
        cursor.close();
        close();
        return returnList;
    }


    public List<VersesModel> getAllVerses(int bookNo, int chapterNo){
        List<VersesModel> versesList =new ArrayList<>();
        open();
        String query="SELECT * FROM t_kjv WHERE b="+bookNo+" AND c="+chapterNo;
        Cursor cursor=myDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
               // int dbId=cursor.getInt(0);
               // int dbBook=cursor.getInt(1);
                //int dbChapter=cursor.getInt(2);
               int verseNo=cursor.getInt(3);
               String txtVerses=cursor.getString(4);

                VersesModel versesModel=new VersesModel(verseNo, txtVerses);
               versesList.add(versesModel);

            }while (cursor.moveToNext());
        }else {
        }
        cursor.close();
        close();
        return versesList;
    }

    //getting the number of the last chapter from the selected book.
    public int bookChapters(int book_index){
        int number=0;
        open();
        String query="SELECT MAX(c) FROM t_kjv WHERE b="+book_index;
        Cursor c=myDatabase.rawQuery(query,null);
        if(c.moveToFirst()){
            number=c.getInt(0);
        }
        close();
        return number;
    }

    public String bookName(int book_index){
        String name="";
        open();
        String query="SELECT n FROM key_english WHERE b="+book_index;
        Cursor c=myDatabase.rawQuery(query,null);
        if(c.moveToFirst()){
            name=c.getString(0);
        }
        close();
        return name;
    }



    //Usage
    // DatabaseHelper dbH = new DatabaseHelper(contextObjecT);
    // dbH.open();
    // dbH.ExampleCommand("en-CA", "en-GB");
    // dbH.close();
    public void ExampleCommand(String myVariable1, String myVariable2)
    {
        String command = "INSERT INTO android_metadata (locale) SELECT ? UNION ALL SELECT ?";
        myDatabase.execSQL(command, new String[]{ myVariable1, myVariable2});
    }

    public void open() throws SQLException{

        try{
            createDatabase();
        }catch (IOException e){
            throw new Error("Unable to create the database");
        }

        try{
            openDatabase();
        }catch (SQLException sqle){
            throw sqle;
        }

    }


    public void createDatabase() throws IOException {
        boolean dbExists=checkDatabase();

        if(dbExists){
        }else{
            this.getReadableDatabase();
            try {
                copyDataBase();
            }catch (IOException e){
                throw new Error("error copying database");
            }
        }
    }

    public SQLiteDatabase getDatabase(){
        String myPath=DATABASE_NAME+DATABASE_NAME;
        return SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    }

    private boolean checkDatabase(){
        SQLiteDatabase checkDB=null;
        try {
            String myPath=DATABASE_PATH+DATABASE_NAME;
            checkDB=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }catch (SQLException e){
        }
        if(checkDB!=null){
            checkDB.close();
        }
        return checkDB!=null ? true:false;
    }

    private void copyDataBase() throws IOException{
       //opening my local db as the input stream.
        InputStream myInput=dbContext.getAssets().open(DATABASE_NAME);

        //path to the just created empty db.
        String outFileNmae=DATABASE_PATH+DATABASE_NAME;

        //Opening the empty db as the output stream.
        OutputStream myOutput=new FileOutputStream(outFileNmae);

        //transfer the bytes from input file to the output file.
        byte[]buffer=new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0) {
            myOutput.write(buffer, 0, length);
        }

        //close all the streams.
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private void openDatabase() throws SQLException{
        //open the database.
        String myPath = DATABASE_PATH+DATABASE_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close(){

        if(myDatabase!=null)
            myDatabase.close();

        super.close();
    }


}
