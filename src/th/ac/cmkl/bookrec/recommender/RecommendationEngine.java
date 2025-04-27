package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;
import java.util.List;

/**
 * RecommendationEngine
 *
 * Interface for all recommendation engines.
 * Defines the method for getting book recommendations for a user.
 *
 * Created by Taha Keler (TAHA)
 * 27 April 2025
 */
public interface RecommendationEngine
{
    /**
     * Generates a list of book recommendations for the given user.
     *
     * @param user the user to recommend books to
     * @return a list of recommended books
     */
    List<Book> getRecommendations(User user);
}
