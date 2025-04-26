package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.PurchaseHistory;
import th.ac.cmkl.bookrec.model.User;
import th.ac.cmkl.bookrec.util.FileStorageHandler;

import java.util.List;

/**
 * Abstract base class for all recommenders.
 * Implements common logic and handles shared book data access.
 */
public abstract class BaseRecommender implements RecommendationEngine {

    /**
     * Shared book database used by all recommenders.
     */
    protected List<Book> bookDatabase;

    /**
     * Constructs a BaseRecommender with a provided book database.
     *
     * @param bookDatabase the list of books available
     */
    public BaseRecommender(List<Book> bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    /**
     * Subclasses must implement this method to provide book recommendations.
     *
     * @param user the user to recommend books to
     * @return a list of recommended books
     */
    @Override
    public abstract List<Book> getRecommendations(User user);

    /**
     * Retrieves the purchase history for the given user.
     *
     * @param user the user whose history to retrieve
     * @return list of purchases the user has made
     */
    protected List<PurchaseHistory> getUserPurchaseHistory(User user) {
        return user.viewPurchaseHistory();
    }

    /**
     * Updates or reloads the book data from file storage.
     */
    public void updateData() {
        this.bookDatabase = FileStorageHandler.loadBooks();
    }
}
