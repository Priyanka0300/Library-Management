package com.library.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean available = true;
    private String borrower;

    public Book() {}

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getBorrower() { return borrower; }
    public void setBorrower(String borrower) { this.borrower = borrower; }

    @Override
    public String toString() {
        return String.format("[%d] %s — %s%s", id, title, author, available ? " (available)" : " (borrowed by: " + borrower + ")");
    }
}
