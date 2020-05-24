package com.example.kjv16;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class VersesFragment extends Fragment {

    DatabaseHelper databaseHelper;
    ListView listView;
    int book;
    int chapterNo;
    Chapter chapter;
    Context vContext;

    public VersesFragment() {
    }

    public VersesFragment(Context context) {
        vContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        databaseHelper = new DatabaseHelper(vContext);
        Bundle args = getArguments();
        chapter = P.chapterFromBundle(args);
        book = chapter.getBook();
        chapterNo = chapter.getChapter();
        View view = inflater.inflate(R.layout.fragment_verses, container, false);
        listView = view.findViewById(R.id.lView);


        List<VersesModel> verses = databaseHelper.getAllVerses(book, chapterNo);
        ArrayAdapter versesArrayAdapter = new ArrayAdapter(vContext, android.R.layout.simple_list_item_1, verses);
        listView.setAdapter(versesArrayAdapter);
        return view;
    }
}








