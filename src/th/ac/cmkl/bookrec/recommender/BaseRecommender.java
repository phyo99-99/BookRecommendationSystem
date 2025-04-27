package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.PurchaseHistory;
import th.ac.cmkl.bookrec.model.User;
import th.ac.cmkl.bookrec.util.FileStorageHandler;

import java.util.List;

/**
 * BaseRecommender
 *
 * Abstract base class for all recommenders.
 * Implements common logic and handles shared book data access.
 *
 * Created by Taha Keler (TAHA)
 * 27 April 2025
 */
public abstract class BaseRecommender implements RecommendationEngine
{
    /** Shared book database used by all recommenders */
    protected List<Book> bookDatabase;

    /**
     * Constructs a BaseRecommender with a provided book database.
     *
     * @param bookDatabase the list of books available
     */
    protected BaseRecommender(List<Book> bookDatabase)
    {
        this.bookDatabase = bookDatabase;
    }

    /**
     * Retrieves the purchase history for the given user.
     *
     * @param user the user whose purchase history to retrieve
     * @return list of purchases the user has made
     */
    protected List<PurchaseHistory> getUserPurchaseHistory(User user)
    {
        return user.viewPurchaseHistory();
    }

    /**
     * Updates or reloads the book data from file storage.
     */
    public void updateData()
    {
        this.bookDatabase = FileStorageHandler.loadBooks();
    }
}
