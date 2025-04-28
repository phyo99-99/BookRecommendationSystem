# Book Recommendation Engine

## Project Overview

This is a Java-based console application that allows users to create profiles, log in, search and purchase books, and get personalized book recommendations.
The system uses persistent storage to save user profiles, purchase histories, and available books, so that the data remains available across program runs.

The main focus of this project is applying object-oriented programming principles and building a simple but functional recommendation engine.

## How It Works

When the program starts, users can either log in or register.
Once logged in, users can:

- Search for books by keyword
- Buy books from the available catalog
- View their purchase history
- Get book recommendations based on:
  - Content-based filtering (same category as books they purchased)
  - Community-based filtering (books bought by other users with similar tastes)

All user data and purchases are saved automatically before exiting.

If no books are available when the program first runs, a set of sample books will be created automatically and saved to storage.

## Project Structure

src/
 └── th/ac/cmkl/bookrec/
     ├── Main.java                 # Console interface and main logic
     ├── model/
     │    ├── Book.java             # Book data model
     │    ├── User.java             # User data model
     │    └── PurchaseHistory.java  # Tracks book purchases
     ├── recommender/
     │    ├── BaseRecommender.java
     │    ├── ContentBasedRecommender.java
     │    ├── CommunityBasedRecommender.java
     │    ├── HybridRecommender.java
     │    └── RecommendationEngine.java
     ├── search/
     │    └── SearchEngine.java     # Keyword-based search
     └── util/
          └── FileStorageHandler.java  # Saving and loading data from files

## Storage

- books.ser – stores the list of available books
- users.ser – stores user profiles and their purchase histories
- purchase_history.ser – (not heavily used separately because purchase history is inside User)

All data is saved using Java's object serialization.

## Dependency Management

There are no external libraries used.  
The project only uses core Java standard libraries like `java.util` and `java.io`.  
Everything is managed manually through the source code.

You can build and run the project using the default Java compiler without any extra dependencies.

## How to Run

1. Compile the project:
   javac -d bin src/th/ac/cmkl/bookrec/**/*.java
2. Run the project:
   java -cp bin th.ac.cmkl.bookrec.Main

You must have Java 8 installed.

## Recommendations Logic

- Content-Based Recommendations:
  Suggests books that are in the same category as previously purchased books but haven't been bought yet.

- Community-Based Recommendations:
  Finds other users who bought similar books and recommends books they purchased that the current user hasn't.

- Hybrid Recommendations:
  Combines both methods to give a broader set of suggestions without duplicates.

## Notes

- No graphical interface (GUI) was used — it is fully text-based through the console.
- New users are created automatically if the username entered doesn't exist.
- Sample books are automatically generated on the first run if no books are found.
- The application focuses on clean object-oriented design, modularity, and extensibility.

## Autohors

- Taha Keler (TAHA)
- Phyo Theingi (PHYO)