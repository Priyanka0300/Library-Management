Library Management System (CLI-Based)

A command-line Library Management System built using Java 17, Maven, and Gson to demonstrate core Java concepts including OOP, collections, file handling, and JSON persistence.

🚀 Features

Add new books
List all books
Borrow a book
Return a book
Persistent storage using JSON
Auto-increment book IDs
Data loads automatically on startup

🛠️ Tech Stack

Java 17
Maven
Gson (JSON serialization/deserialization)
File I/O
Collections Framework (ArrayList)

Project Structure
```
LibraryMangmt/
│── pom.xml
│── src/
│   └── main/
│       └── java/
│           └── com/library/
│               ├── LibraryApp.java     (CLI Layer)
│               ├── Library.java        (Business Logic)
│               └── model/
│                   └── Book.java       (Model Class)
```
🧠 Architecture Overview
```
User (CLI Input)
        ↓
LibraryApp (Menu & Input Handling)
        ↓
Library (Business Logic & Operations)
        ↓
Book (Data Model)
        ↓
library.json (Persistence using Gson)
```
💾 Persistence Strategy
The application uses Gson to serialize the entire Library object into a JSON file.
Example library.json:
```json
{
  "books": [
    {
      "id": 1,
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "available": false,
      "borrower": "Alice"
    }
  ],
  "nextId": 2
}
```

▶️ How To Build & Run

1️⃣ Clone the repository
```bash
git clone https://github.com/your-username/library-management-cli.git
cd library-management-cli
```
2️⃣ Build the project

```bash
mvn clean package
```
3️⃣ Run the application
Option A – Using Maven:
```bash
mvn exec:java -Dexec.mainClass="com.library.LibraryApp"
```
Option B – Run test_cli.bat:
```bash
test_cli.bat
```

📋 Sample CLI Menu
1) Add Book
2) List Books
3) Borrow Book
4) Return Book
5) Save
6) Exit

🎯 Concepts Demonstrated

Object-Oriented Programming (OOP)

Encapsulation

Separation of Concerns

Collections (ArrayList)

File Handling

JSON Serialization & Deserialization

Maven Build Lifecycle
