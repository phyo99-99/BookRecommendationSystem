package th.ac.cmkl.bookrec.search;

import th.ac.cmkl.bookrec.model.Book;
import java.util.List;
import java.util.ArrayList;

/**
 * SearchEngine
 *
 * Handles keyword-based search for books.
 *
 * Created by Taha Keler (TAHA) and Phyo Theingi (PHYO)
 * 27 April 2025
 */
public class SearchEngine
{
    private SearchEngine()
    {
        // prevent instantiation
    }

    public static List<Book> search(List<Book> books, String query)
    {
        List<Book> results = new ArrayList<>();
        for (Book book : books)
        {
            if (book.matchesQuery(query))
            {
                results.add(book);
            }
        }
        return results;
    }
}
