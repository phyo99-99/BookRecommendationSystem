package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;
import java.util.List;
import java.util.ArrayList;

/**
 * Recommender that combines content and community-based strategies.
 */
public class HybridRecommender extends BaseRecommender {

    public HybridRecommender(List<Book> bookDatabase) {
        super(bookDatabase);
    }

    @Override
    public List<Book> getRecommendations(User user) {
        // Implement hybrid recommendation logic
        return new ArrayList<>();
    }
}
