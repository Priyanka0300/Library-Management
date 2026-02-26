package com.library;

import com.library.model.Book;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    private static final String DATA_FILE = "library.json";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib;
        File f = new File(DATA_FILE);
        if (f.exists()) {
            try {
                lib = Library.loadFromFile(DATA_FILE);
                System.out.println("Loaded library from " + DATA_FILE);
            } catch (IOException e) {
                System.out.println("Failed to load data, starting empty library.");
                lib = new Library();
            }
        } else {
            lib = new Library();
        }

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("Choose> ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> addBook(lib, sc);
                case "2" -> listBooks(lib);
                case "3" -> borrowBook(lib, sc);
                case "4" -> returnBook(lib, sc);
                case "5" -> {
                    try {
                        lib.saveToFile(DATA_FILE);
                        System.out.println("Saved to " + DATA_FILE);
                    } catch (IOException e) {
                        System.out.println("Save failed: " + e.getMessage());
                    }
                }
                case "6" -> {
                    running = false;
                    System.out.println("Exiting.");
                }
                default -> System.out.println("Unknown option.");
            }
        }
        sc.close();
    }

    private static void showMenu() {
        System.out.println("--- Library CLI ---");
        System.out.println("1) Add book");
        System.out.println("2) List books");
        System.out.println("3) Borrow book");
        System.out.println("4) Return book");
        System.out.println("5) Save");
        System.out.println("6) Exit");
    }

    private static void addBook(Library lib, Scanner sc) {
        System.out.print("Title: ");
        String title = sc.nextLine().trim();
        System.out.print("Author: ");
        String author = sc.nextLine().trim();
        Book b = lib.addBook(title, author);
        System.out.println("Added: " + b);
    }

    private static void listBooks(Library lib) {
        List<Book> books = lib.listBooks();
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book b : books) System.out.println(b);
    }

    private static void borrowBook(Library lib, Scanner sc) {
        System.out.print("Book ID to borrow: ");
        String s = sc.nextLine().trim();
        try {
            int id = Integer.parseInt(s);
            System.out.print("Borrower name: ");
            String name = sc.nextLine().trim();
            if (lib.borrowBook(id, name)) System.out.println("Borrowed successfully.");
            else System.out.println("Cannot borrow (not found or already borrowed).");
        } catch (NumberFormatException e) {
            System.out.println("Invalid id.");
        }
    }

    private static void returnBook(Library lib, Scanner sc) {
        System.out.print("Book ID to return: ");
        String s = sc.nextLine().trim();
        try {
            int id = Integer.parseInt(s);
            if (lib.returnBook(id)) System.out.println("Returned successfully.");
            else System.out.println("Cannot return (not found or not borrowed).");
        } catch (NumberFormatException e) {
            System.out.println("Invalid id.");
        }
    }
}
