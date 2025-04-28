package th.ac.cmkl.bookrec.model;

import java.io.Serializable;
import java.util.List;

/**
 * Book
 *
 * Represents a book with searchable and comparable attributes.
 *
 * Created by Taha Keler (TAHA) and Phyo Theingi (PHYO)
 * 27 April 2025
 */
public class Book implements Serializable
{
    /** Title of the book */
    private String title;

    /** Author of the book */
    private String author;

    /** Category or genre of the book */
    private String category;

    /** List of searchable keywords */
    private List<String> keywords;

    public Book(String title, String author, String category, List<String> keywords)
    {
        this.title = title;
        this.author = author;
        this.category = category;
        this.keywords = keywords;
    }

    public boolean matchesQuery(String query)
    {
        return title.contains(query) || author.contains(query) || keywords.contains(query);
    }

    public boolean isSimilar(Book other)
    {
        return this.category.equals(other.category);
    }

    public String getCategory()
    {
        return category;
    }

    /**
     * Returns the title of the book.
     *
     * @return the book title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the book author
     */
    public String getAuthor()
    {
        return author;
    }
    /**
     * Returns the list of keywords for the book.
     *
     * @return the list of keywords
     */
    public List<String> getKeywords()
    {
        return keywords;
    }
}
