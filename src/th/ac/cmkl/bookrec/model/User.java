package th.ac.cmkl.bookrec.model;

import th.ac.cmkl.bookrec.search.SearchEngine;
import th.ac.cmkl.bookrec.recommender.RecommendationEngine;
import th.ac.cmkl.bookrec.recommender.HybridRecommender;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User
 *
 * Represents a user in the system.
 *
 * Created by Taha Keler (TAHA)
 * 27 April 2025
 */
public class User implements Serializable
{
    /** Username of the user */
    private String username;

    /** Password of the user */
    private String password;

    /** List of purchased books */
    private List<PurchaseHistory> purchaseHistories;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.purchaseHistories = new ArrayList<>();
    }

    public boolean login(String username, String password)
    {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void buyBook(Book book)
    {
        purchaseHistories.add(new PurchaseHistory(book, new java.util.Date()));
    }

    public List<Book> searchBooks(List<Book> books, String query)
    {
        return SearchEngine.search(books, query);
    }

    public List<Book> getRecommendations(List<Book> allBooks)
    {
        RecommendationEngine engine = new HybridRecommender(allBooks);
        return engine.getRecommendations(this);
    }

    public List<PurchaseHistory> viewPurchaseHistory()
    {
        return purchaseHistories;
    }

    public String getUsername()
    {
        return username;
    }

    public List<PurchaseHistory> getPurchaseHistories()
    {
        return purchaseHistories;
    }
}
