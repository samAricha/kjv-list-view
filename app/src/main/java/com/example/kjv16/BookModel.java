package com.example.kjv16;

public class BookModel {
    int b;
    String n;
    String t;
    int g;

    public BookModel(int b, String n) {
        this.b = b;
        this.n = n;
    }

    @Override
    public String toString() {
        return b +". "+ n ;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }
}
