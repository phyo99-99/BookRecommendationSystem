package th.ac.cmkl.bookrec.recommender;

import th.ac.cmkl.bookrec.model.Book;
import th.ac.cmkl.bookrec.model.User;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * HybridRecommender
 *
 * This class combines content-based and community-based recommendations.
 *
 * Created by Taha Keler (TAHA) and Phyo Theingi (PHYO)
 * 27 April 2025
 */
public class HybridRecommender extends BaseRecommender
{
    /**
     * Constructs a HybridRecommender with a provided book database.
     *
     * @param bookDatabase the list of all available books
     */
    public HybridRecommender(List<Book> bookDatabase)
    {
        super(bookDatabase);
    }

    /**
     * Combines content-based and community-based recommendations.
     * Ensures no duplicate books are recommended.
     *
     * @param user the user to recommend books to
     * @return a list of recommended books
     */
    @Override
    public List<Book> getRecommendations(User user)
    {
        Set<Book> combinedRecommendations = new HashSet<>();

        /* Create a content-based recommender and fetch recommendations */
        ContentBasedRecommender contentBased = new ContentBasedRecommender(bookDatabase);
        List<Book> contentRecommendations = contentBased.getRecommendations(user);

        /* Create a community-based recommender and fetch recommendations */
        CommunityBasedRecommender communityBased = new CommunityBasedRecommender(bookDatabase);
        List<Book> communityRecommendations = communityBased.getRecommendations(user);

        /* Add all recommendations from both sources, automatically avoiding duplicates */
        combinedRecommendations.addAll(contentRecommendations);
        combinedRecommendations.addAll(communityRecommendations);

        return new ArrayList<>(combinedRecommendations);
    }
}
