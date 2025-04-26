package th.ac.cmkl.bookrec.search;

import th.ac.cmkl.bookrec.model.Book;
import java.util.List;
import java.util.ArrayList;

/**
 * Handles keyword-based search for books.
 */
public class SearchEngine {

    public static List<Book> search(List<Book> books, String query) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.matchesQuery(query)) {
                results.add(book);
            }
        }
        return results;
    }
}
