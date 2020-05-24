package com.example.kjv16;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lView;
    DatabaseHelper dbHelper;
    List<BookModel>bookList;
    Spinner books, chapter;
    ArrayAdapter booksArrayAdapter,bookSpinnerAdapter,chapterSpinnerAdapter;
    Button open;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lView=findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(MainActivity.this);
        books=(Spinner)findViewById(R.id.book);
        chapter=(Spinner)findViewById(R.id.chapter);
        displayBooks();
        chapterSpinnerAdapter=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line);
        chapter.setAdapter(chapterSpinnerAdapter);

        //listener for the list view.
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getBaseContext(), Verses.class);
                intent.putExtra("bookNo",position+1);
                intent.putExtra("chapterNo",1);
                startActivity(intent);
            }
        });

        //listener for the recycler view.
        books.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  chapterSpinnerAdapter.clear();
                  position=(position+1); //+1 for the position of the book.
                  int maxChapters=dbHelper.bookChapters(position)+1;//+1 for the last chapter of the selected book.
                  for(int a=1;a<maxChapters;a++) {
                      chapterSpinnerAdapter.add("ch "+a);
                  }
            }
           @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        open=findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),Verses.class);
                intent.putExtra("bookNo",books.getSelectedItemPosition()+1);
                intent.putExtra("chapterNo",chapter.getSelectedItemPosition());
                startActivity(intent);
            }
        });


    }

    private void displayBooks(){
        bookList=new ArrayList<>();
        bookList=dbHelper.getBooks();

        booksArrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_dropdown_item_1line,bookList);
        lView.setAdapter(booksArrayAdapter);
        bookSpinnerAdapter=new ArrayAdapter(MainActivity.this, android.R.layout.simple_dropdown_item_1line,bookList);
        books.setAdapter(bookSpinnerAdapter);
    }

}