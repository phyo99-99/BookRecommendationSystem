package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;
import java.util.List;

/**
 * Interface for recommendation engines.
 */
public interface RecommendationEngine {
    List<Book> getRecommendations(User user);
}
