package com.example.kjv16;

public class VersesModel {
    int id;
    int book;
    int chapter;
    int verseNo;
    String verses;


    public VersesModel(){

    }

    public VersesModel(int verseNo, String verses) {
       //this.id=id;
       // this.book=book;
       // this.chapter=chapter;
        this.verseNo = verseNo;
        this.verses = verses;
    }

    //the toString method is necessary if we are going to PRINT this model.

    @Override
    public String toString() {
        return verseNo+" "+verses;
    }


    //getters and setters.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public int getVerseNo() {
        return verseNo;
    }

    public void setVerseNo(int verseNo) {
        this.verseNo= verseNo;
    }

    public String getVerses() {
        return verses;
    }

    public void setVerses(String verses) {
        this.verses = verses;
    }
}
