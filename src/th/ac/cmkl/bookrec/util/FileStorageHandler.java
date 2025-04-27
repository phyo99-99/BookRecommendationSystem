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
 * Created by Taha Keler (TAHA)
 * 27 April 2025
 */
public class FileStorageHandler
{
    private FileStorageHandler()
    {
        // prevent instantiation
    }

    private static final String USERS_FILE = "users.ser";
    private static final String BOOKS_FILE = "books.ser";
    private static final String PURCHASE_HISTORY_FILE = "purchase_history.ser";

    @SuppressWarnings("unchecked")
    public static List<User> loadUsers()
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(USERS_FILE)))
        {
            return (List<User>) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            return new ArrayList<>();
        }
    }

    public static void saveUsers(List<User> users)
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(USERS_FILE)))
        {
            outputStream.writeObject(users);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> loadBooks()
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(BOOKS_FILE)))
        {
            return (List<Book>) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            return new ArrayList<>();
        }
    }

    public static void saveBooks(List<Book> books)
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(BOOKS_FILE)))
        {
            outputStream.writeObject(books);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<PurchaseHistory> loadPurchaseHistory()
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PURCHASE_HISTORY_FILE)))
        {
            return (List<PurchaseHistory>) inputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            return new ArrayList<>();
        }
    }

    public static void savePurchaseHistory(List<PurchaseHistory> history)
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PURCHASE_HISTORY_FILE)))
        {
            outputStream.writeObject(history);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}