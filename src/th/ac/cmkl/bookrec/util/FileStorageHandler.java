package th.ac.cmkl.bookrec.util;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;
import th.ac.cmkl.bookrec.model.PurchaseHistory;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * FileStorageHandler
 *
 * Handles loading and saving of Users, Books, and PurchaseHistory to files.
 *
 * Created by Taha Keler (TAHA) and Phyo Theingi (PHYO)
 * 27 April 2025
 */
public class FileStorageHandler
{
    private FileStorageHandler()
    {
        // Prevent instantiation of this utility class
    }

    // File paths for serialization
    private static final String USERS_FILE = "users.ser";
    private static final String BOOKS_FILE = "books.ser";
    private static final String PURCHASE_HISTORY_FILE = "purchase_history.ser";

    /**
     * Loads the list of users from the users.ser file.
     *
     * @return the list of users
     */
    @SuppressWarnings("unchecked")
    public static List<User> loadUsers()
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(USERS_FILE)))
        {
            return (List<User>) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            return new ArrayList<>();  // Return an empty list if the file is not found
        }
    }

    /**
     * Saves the list of users to the users.ser file.
     *
     * @param users the list of users to save
     */
    public static void saveUsers(List<User> users)
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(USERS_FILE)))
        {
            outputStream.writeObject(users);
        }
        catch (IOException e)
        {
            e.printStackTrace();  // Handle error while saving users
        }
    }

    /**
     * Loads the list of books from the books.ser file.
     *
     * @return the list of books
     */
    @SuppressWarnings("unchecked")
    public static List<Book> loadBooks()
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(BOOKS_FILE)))
        {
            return (List<Book>) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            return new ArrayList<>();  // Return an empty list if the file is not found
        }
    }

    /**
     * Saves the list of books to the books.ser file.
     *
     * @param books the list of books to save
     */
    public static void saveBooks(List<Book> books)
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE)))
        {
            outputStream.writeObject(books);  // Save the list of books to the file
        }
        catch (IOException e)
        {
            e.printStackTrace();  // Handle error while saving books
        }
    }

    /**
     * Adds a new book to the list of books and saves it to the books.ser file.
     *
     * @param newBook the book to add
     * @param books the current list of books
     */
    public static void addBook(Book newBook, List<Book> books)
    {
        books.add(newBook);  // Add the new book to the list
        saveBooks(books);  // Save the updated list of books to the file
    }

    /**
     * Removes a book from the list of books and saves it to the books.ser file.
     *
     * @param bookToRemove the book to remove
     * @param books the current list of books
     */
    public static void removeBook(Book bookToRemove, List<Book> books)
    {
        books.remove(bookToRemove);  // Remove the book from the list
        saveBooks(books);  // Save the updated list to the file
    }

    /**
     * Loads the purchase history from the purchase_history.ser file.
     *
     * @return the list of purchase history
     */
    @SuppressWarnings("unchecked")
    public static List<PurchaseHistory> loadPurchaseHistory()
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PURCHASE_HISTORY_FILE)))
        {
            return (List<PurchaseHistory>) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            return new ArrayList<>();  // Return an empty list if the file is not found
        }
    }

    /**
     * Saves the purchase history to the purchase_history.ser file.
     *
     * @param history the purchase history to save
     */
    public static void savePurchaseHistory(List<PurchaseHistory> history)
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PURCHASE_HISTORY_FILE)))
        {
            outputStream.writeObject(history);  // Save the purchase history to the file
        }
        catch (IOException e)
        {
            e.printStackTrace();  // Handle error while saving purchase history
        }
    }
}
