package ru.netology.domain;

public class Book extends Product {

    protected String author;

    public String getAuthor() {
        return author;
    }

    public Book(int id, String name, int price, String author) {
        super(id, name, price);
        this.author = author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}
