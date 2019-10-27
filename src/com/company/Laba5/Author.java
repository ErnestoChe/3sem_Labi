package com.company.Laba5;


import java.util.ArrayList;

public class Author {
    private String name;
    private ArrayList<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void addBook(Book b){
        books.add(b);
    }

    //конструктор
    public Author(){
        books = new ArrayList<>();
    }

    public Author(String name, ArrayList<Book> book){
        this.name=name;
        this.books=book;
    }

    public String toString(){
        return "author = "+ name;
    }
}

class Book{
    private String title;
    private int pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}