package th.ac.cmkl.bookrec.model;

import th.ac.cmkl.bookrec.recommender.HybridRecommender;
import th.ac.cmkl.bookrec.recommender.RecommendationEngine;
import th.ac.cmkl.bookrec.search.SearchEngine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User
 *
 * Represents a user in the system with capabilities to login,
 * search for books, receive recommendations, and view purchase history.
 *
 * Created by Taha Keler and Phyo Theingi
 * April 27, 2025
 */

public class User
{
    /** Username for login */
    private String username;

    /** Password for login */
    private String password;

    /** List of user's purchase histories */
    private List<PurchaseHistory> purchaseHistories;

    /**
     * Constructs a User with a username and password.
     *
     * @param username The user's username.
     * @param password The user's password.
     */
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.purchaseHistories = new ArrayList<>();
    }

    /**
     * Validates the user's login credentials.
     *
     * @param username Input username.
     * @param password Input password.
     * @return true if credentials match; false otherwise.
     */
    public boolean login(String username, String password)
    {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Records a book purchase into the user's history.
     *
     * @param book The book being purchased.
     */
    public void buyBook(Book book)
    {
        purchaseHistories.add(new PurchaseHistory(book, new Date()));
    }

    /**
     * Searches for books using a keyword through the SearchEngine utility.
     *
     * @param books The list of available books.
     * @param query The keyword to search.
     * @return List of books matching the query.
     */
    public List<Book> searchBooks(List<Book> books, String query)
    {
        return SearchEngine.search(books, query);
    }

    /**
     * Retrieves book recommendations using the hybrid recommendation strategy.
     *
     * @param allBooks The complete list of books.
     * @return A list of recommended books.
     */
    public List<Book> getRecommendations(List<Book> allBooks)
    {
        RecommendationEngine engine = new HybridRecommender(allBooks);
        return engine.getRecommendations(this);
    }

    /**
     * Retrieves the user's purchase history.
     *
     * @return List of PurchaseHistory objects.
     */
    public List<PurchaseHistory> viewPurchaseHistory()
    {
        return purchaseHistories;
    }

    /**
     * Gets the user's username.
     *
     * @return Username.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Gets the user's full purchase history.
     *
     * @return List of PurchaseHistory.
     */
    public List<PurchaseHistory> getPurchaseHistories()
    {
        return purchaseHistories;
    }
}
