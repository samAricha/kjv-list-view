package com.example.kjv16;

import android.os.Bundle;

public class P {


    public static Chapter chapterFromBundle(Bundle b){
        return new Chapter(b.getInt("book"),b.getInt("chapter"));
    }

    public static Bundle chapterToBundle(Chapter c){
        Bundle b=new Bundle();
        b.putInt("book", c.getBook());
        b.putInt("chapter", c.getChapter());
        return b;
    }
}
