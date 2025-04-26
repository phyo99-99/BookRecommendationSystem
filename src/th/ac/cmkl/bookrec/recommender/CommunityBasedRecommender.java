package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;
import java.util.List;
import java.util.ArrayList;

/**
 * Recommender that suggests books based on community behavior.
 */
public class CommunityBasedRecommender extends BaseRecommender {

    public CommunityBasedRecommender(List<Book> bookDatabase) {
        super(bookDatabase);
    }

    @Override
    public List<Book> getRecommendations(User user) {
        // Implement community-based recommendation logic
        return new ArrayList<>();
    }
}
