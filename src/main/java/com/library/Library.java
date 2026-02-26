package com.library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.library.model.Book;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private int nextId = 1;

    public Library() {}

    public List<Book> getBooks() { return books; }

    public Book addBook(String title, String author) {
        Book b = new Book(nextId++, title, author);
        books.add(b);
        return b;
    }

    public List<Book> listBooks() { return books; }

    public boolean borrowBook(int id, String borrower) {
        for (Book b : books) {
            if (b.getId() == id && b.isAvailable()) {
                b.setAvailable(false);
                b.setBorrower(borrower);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(int id) {
        for (Book b : books) {
            if (b.getId() == id && !b.isAvailable()) {
                b.setAvailable(true);
                b.setBorrower(null);
                return true;
            }
        }
        return false;
    }

    public void saveToFile(String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter w = new FileWriter(path)) {
            gson.toJson(this, w);
        }
    }

    public static Library loadFromFile(String path) throws IOException {
        Gson gson = new Gson();
        try (FileReader r = new FileReader(path)) {
            Type t = new TypeToken<Library>(){}.getType();
            Library lib = gson.fromJson(r, t);
            if (lib == null) return new Library();
            // ensure nextId is correct
            int max = 0;
            for (Book b : lib.getBooks()) if (b.getId() > max) max = b.getId();
            lib.nextId = max + 1;
            return lib;
        }
    }
}
