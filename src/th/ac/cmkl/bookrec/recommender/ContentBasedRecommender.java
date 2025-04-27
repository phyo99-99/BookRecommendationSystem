package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.PurchaseHistory;
import th.ac.cmkl.bookrec.model.User;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * ContentBasedRecommender
 *
 * This class recommends books based on content similarity to the books the user has purchased.
 *
 * Created by Taha Keler (TAHA)
 * 27 April 2025
 */
public class ContentBasedRecommender extends BaseRecommender
{
    /**
     * Constructs a ContentBasedRecommender with a provided book database.
     *
     * @param bookDatabase the list of all available books
     */
    public ContentBasedRecommender(List<Book> bookDatabase)
    {
        super(bookDatabase);
    }

    /**
     * Recommends books to the user based on the content similarity of books they have purchased.
     * Books are considered similar if they share the same category.
     *
     * @param user the user to recommend books to
     * @return a list of recommended books
     */
    @Override
    public List<Book> getRecommendations(User user)
    {
        List<Book> recommendations = new ArrayList<>();
        List<PurchaseHistory> userHistory = getUserPurchaseHistory(user);
        Set<String> purchasedCategories = new HashSet<>();
        Set<Book> alreadyPurchased = new HashSet<>();

        /* Collect categories and books the user already purchased */
        for (PurchaseHistory history : userHistory)
        {
            Book purchasedBook = history.getBook();
            purchasedCategories.add(purchasedBook.getCategory());
            alreadyPurchased.add(purchasedBook);
        }

        /* Recommend books from the same category that the user has not purchased yet */
        for (Book book : bookDatabase)
        {
            if (purchasedCategories.contains(book.getCategory()) && !alreadyPurchased.contains(book))
            {
                recommendations.add(book);
            }
        }

        return recommendations;
    }
}
