package com.example.kjv16;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class Verses extends AppCompatActivity  {
    ListView versesLView;
    DatabaseHelper databaseHelper;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    int book,chapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verses);
        Intent intent = getIntent();
        book = intent.getIntExtra("bookNo", 1);
        chapter = intent.getIntExtra("chapterNo", 1);
        databaseHelper = new DatabaseHelper(Verses.this);
        viewPager = findViewById(R.id.vPager);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        populateChapters();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(chapter);
            }
        },1);
    }

    public void populateChapters(){
        adapter.clearAll();
        //the +1 is for the last chapter.
        int maxChapters=databaseHelper.bookChapters(book)+1;
        String bookName=databaseHelper.bookName(book);

            for (int a = 1; a < maxChapters; a++) {
                VersesFragment versesFragment = new VersesFragment(Verses.this);
                Bundle bundle = P.chapterToBundle(new Chapter(book, a));
                versesFragment.setArguments(bundle);
                adapter.addFrag(versesFragment, bookName + " " + a);

            }
        adapter.notifyDataSetChanged();
    }


}