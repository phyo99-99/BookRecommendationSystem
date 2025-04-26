package th.ac.cmkl.bookrec.util;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;
import th.ac.cmkl.bookrec.model.PurchaseHistory;

import java.util.List;
import java.util.ArrayList;

/**
 * Handles loading and saving of data to files.
 * NOTE: Implement actual file I/O logic as needed.
 */
public class FileStorageHandler {

    /**
     * Loads the list of users from a file.
     *
     * @return list of users
     */
    public static List<User> loadUsers() {
        // TODO: Implement file loading logic for users
        return new ArrayList<>();
    }

    /**
     * Saves the list of users to a file.
     *
     * @param users the list of users to save
     */
    public static void saveUsers(List<User> users) {
        // TODO: Implement file saving logic for users
    }

    /**
     * Loads the list of books from a file.
     *
     * @return list of books
     */
    public static List<Book> loadBooks() {
        // TODO: Implement file loading logic for books
        return new ArrayList<>();
    }

    /**
     * Saves the list of books to a file.
     *
     * @param books the list of books to save
     */
    public static void saveBooks(List<Book> books) {
        // TODO: Implement file saving logic for books
    }

    /**
     * Loads the purchase history from a file.
     *
     * @return list of purchase records
     */
    public static List<PurchaseHistory> loadPurchaseHistory() {
        // TODO: Implement file loading logic for purchase history
        return new ArrayList<>();
    }

    /**
     * Saves the purchase history to a file.
     *
     * @param history the purchase history to save
     */
    public static void savePurchaseHistory(List<PurchaseHistory> history) {
        // TODO: Implement file saving logic for purchase history
    }
}
