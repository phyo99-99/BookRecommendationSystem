package th.ac.cmkl.bookrec.model;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.PurchaseHistory;
import th.ac.cmkl.bookrec.search.SearchEngine;
import th.ac.cmkl.bookrec.recommender.RecommendationEngine;
import th.ac.cmkl.bookrec.recommender.HybridRecommender;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the system.
 */
public class User {

    private String username;
    private String password;
    private List<PurchaseHistory> purchaseHistories;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.purchaseHistories = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void buyBook(Book book) {
        purchaseHistories.add(new PurchaseHistory(book, new java.util.Date()));
    }

    /**
     * Searches books using the SearchEngine utility.
     *
     * @param books the list of all books
     * @param query the keyword to search
     * @return matching books
     */
    public List<Book> searchBooks(List<Book> books, String query) {
        return SearchEngine.search(books, query);
    }

    /**
     * Gets recommendations using the default hybrid strategy.
     *
     * @param allBooks the complete list of books
     * @return a list of recommended books
     */
    public List<Book> getRecommendations(List<Book> allBooks) {
        RecommendationEngine engine = new HybridRecommender(allBooks);
        return engine.getRecommendations(this);
    }

    public List<PurchaseHistory> viewPurchaseHistory() {
        return purchaseHistories;
    }

    public String getUsername() {
        return username;
    }

    public List<PurchaseHistory> getPurchaseHistories() {
        return purchaseHistories;
    }
}
