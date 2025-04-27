package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.PurchaseHistory;
import th.ac.cmkl.bookrec.model.User;
import th.ac.cmkl.bookrec.util.FileStorageHandler;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * CommunityBasedRecommender
 *
 * This class recommends books based on what other users with similar purchases have bought.
 *
 * Created by Taha Keler (TAHA)
 * 27 April 2025
 */
public class CommunityBasedRecommender extends BaseRecommender
{
    /**
     * Constructs a CommunityBasedRecommender with a provided book database.
     *
     * @param bookDatabase the list of all available books
     */
    public CommunityBasedRecommender(List<Book> bookDatabase)
    {
        super(bookDatabase);
    }

    /**
     * Recommends books based on community behavior by finding users with similar purchases.
     *
     * @param user the user to recommend books to
     * @return a list of recommended books
     */
    @Override
    public List<Book> getRecommendations(User user)
    {
        List<Book> recommendations = new ArrayList<>();
        Set<Book> alreadyPurchased = getUserPurchasedBooks(user);
        Set<Book> communityRecommendations = findCommunityRecommendations(user, alreadyPurchased);

        recommendations.addAll(communityRecommendations);
        return recommendations;
    }

    /**
     * Retrieves all books the user has already purchased.
     *
     * @param user the user
     * @return a set of books already purchased
     */
    private Set<Book> getUserPurchasedBooks(User user)
    {
        Set<Book> purchasedBooks = new HashSet<>();
        List<PurchaseHistory> history = getUserPurchaseHistory(user);

        for (PurchaseHistory purchase : history)
        {
            purchasedBooks.add(purchase.getBook());
        }

        return purchasedBooks;
    }

    /**
     * Finds recommended books based on similar users' purchase histories.
     *
     * @param user the current user
     * @param alreadyPurchased books the user already owns
     * @return a set of books recommended from the community
     */
    private Set<Book> findCommunityRecommendations(User user, Set<Book> alreadyPurchased)
    {
        Set<Book> communityBooks = new HashSet<>();
        List<User> allUsers = FileStorageHandler.loadUsers();

        for (User otherUser : allUsers)
        {
            if (!otherUser.getUsername().equals(user.getUsername()) && hasSharedPurchase(otherUser, alreadyPurchased))
            {
                addOtherUserPurchases(otherUser, alreadyPurchased, communityBooks);
            }
        }

        return communityBooks;
    }

    /**
     * Checks if another user shares at least one purchase with the current user.
     *
     * @param otherUser the other user
     * @param alreadyPurchased books already purchased by the current user
     * @return true if a shared purchase exists
     */
    private boolean hasSharedPurchase(User otherUser, Set<Book> alreadyPurchased)
    {
        for (PurchaseHistory purchase : otherUser.viewPurchaseHistory())
        {
            if (alreadyPurchased.contains(purchase.getBook()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds books from another user to community recommendations if they are not already purchased.
     *
     * @param otherUser the other user
     * @param alreadyPurchased books already purchased by the current user
     * @param communityBooks collection of community recommended books
     */
    private void addOtherUserPurchases(User otherUser, Set<Book> alreadyPurchased, Set<Book> communityBooks)
    {
        for (PurchaseHistory purchase : otherUser.viewPurchaseHistory())
        {
            Book book = purchase.getBook();
            if (!alreadyPurchased.contains(book))
            {
                communityBooks.add(book);
            }
        }
    }
}
